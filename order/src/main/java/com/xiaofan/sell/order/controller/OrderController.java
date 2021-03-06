package com.xiaofan.sell.order.controller;

import com.xiaofan.sell.api.utils.ResultVOUtil;
import com.xiaofan.sell.api.vo.ResultVO;
import com.xiaofan.sell.order.client.ProductClient;
import com.xiaofan.sell.order.converter.OrderForm2OrderDTOConverter;
import com.xiaofan.sell.order.dto.CartDTO;
import com.xiaofan.sell.order.dto.OrderDTO;
import com.xiaofan.sell.order.enums.ResultEnum;
import com.xiaofan.sell.order.exception.OrderException;
import com.xiaofan.sell.order.form.OrderForm;
import com.xiaofan.sell.api.pojo.ProductInfo;
import com.xiaofan.sell.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductClient productClient;

    @PostMapping("/create")
    public ResultVO create(@Valid OrderForm orderForm, BindingResult bindingResult){
        //1.参数校验
        if (bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        // orderForm -> orderDTO
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetails())) {
            log.error("【创建订单】购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }
        //2.创建订单
        OrderDTO result = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVOUtil.success(map);
    }

    @PostMapping("/listForOrder")
    public List<ProductInfo> listForOrder(){
       return productClient.listForOrder(Arrays.asList("157875196366160022","157875227953464068"));
    }

    @PostMapping("/decrStock")
    public void decrStock(){
         productClient.decrStock(Arrays.asList(new CartDTO("164103465734242707",2)));
    }

}
