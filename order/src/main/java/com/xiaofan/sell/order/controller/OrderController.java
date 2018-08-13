package com.xiaofan.sell.order.controller;

import com.xiaofan.sell.order.converter.OrderForm2OrderDTOConverter;
import com.xiaofan.sell.order.dto.OrderDTO;
import com.xiaofan.sell.order.enums.ResultEnum;
import com.xiaofan.sell.order.exception.OrderException;
import com.xiaofan.sell.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    public void create(@Valid OrderForm orderForm,BindingResult bindingResult){
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
    }
}
