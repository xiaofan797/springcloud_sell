package com.xiaofan.sell.order.service.impl;

import com.xiaofan.sell.api.pojo.ProductInfo;
import com.xiaofan.sell.order.client.ProductClient;
import com.xiaofan.sell.order.dao.OrderDetailMapper;
import com.xiaofan.sell.order.dto.CartDTO;
import com.xiaofan.sell.order.pojo.OrderDetail;
import com.xiaofan.sell.order.service.OrderService;
import com.xiaofan.sell.order.dao.OrderMasterMapper;
import com.xiaofan.sell.order.dto.OrderDTO;
import com.xiaofan.sell.order.enums.OrderStatusEnum;
import com.xiaofan.sell.order.enums.PayStatusEnum;
import com.xiaofan.sell.order.pojo.OrderMaster;
import com.xiaofan.sell.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMasterMapper orderMasterMapper;
    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Autowired
    ProductClient productClient;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.generateKey();
        //1.查找商品信息（调用商品服务）
        List<String> productIdList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDTO.getOrderDetails()) {
            String productId = orderDetail.getProductId();
            productIdList.add(productId);
        }
        List<ProductInfo> productInfoList = productClient.listForOrder(productIdList);
        // 2.计算订单总价
        BigDecimal orderAmount = BigDecimal.ZERO;
        List<CartDTO> cartDTOList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDTO.getOrderDetails()) {
            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    orderAmount = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);
                    //订单详情入库
                    //BeanUtils.copyProperties(productInfo,orderDetail);
                    orderDetail.setProductId(productInfo.getProductId());
                    orderDetail.setProductIcon(productInfo.getProductIcon());
                    orderDetail.setProductName(productInfo.getProductName());
                    orderDetail.setProductPrice(productInfo.getProductPrice());
                    orderDetail.setDetailId(KeyUtil.generateKey());
                    orderDetail.setOrderId(orderId);

                    orderDetailMapper.add(orderDetail);
                }
            }
            cartDTOList.add(cartDTO);
        }
        //3.扣费库存（调用商品服务）
        productClient.decrStock(cartDTOList);
        //4.订单入库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderAmount(orderAmount);
        orderMasterMapper.add(orderMaster);
        orderDTO.setOrderId(orderId);
        return orderDTO;
    }
}
