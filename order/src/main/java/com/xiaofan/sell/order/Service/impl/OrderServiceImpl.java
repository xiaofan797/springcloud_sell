package com.xiaofan.sell.order.Service.impl;

import com.xiaofan.sell.order.Service.OrderService;
import com.xiaofan.sell.order.dao.OrderMasterMapper;
import com.xiaofan.sell.order.dto.OrderDTO;
import com.xiaofan.sell.order.enums.OrderStatusEnum;
import com.xiaofan.sell.order.enums.PayStatusEnum;
import com.xiaofan.sell.order.pojo.OrderMaster;
import com.xiaofan.sell.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMasterMapper orderMasterMapper;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        //TODO 1.查找商品信息（调用商品服务）
        //TODO 2.计算订单总价
        //TODO 3.扣费库存（调用商品服务）

        //4.订单入库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        String orderId = KeyUtil.generateKey();
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterMapper.add(orderMaster);
        orderDTO.setOrderId(orderId);
        return orderDTO;
    }
}
