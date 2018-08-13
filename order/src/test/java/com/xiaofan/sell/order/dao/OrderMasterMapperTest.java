package com.xiaofan.sell.order.dao;

import com.xiaofan.sell.order.pojo.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterMapperTest {

    @Autowired
    OrderMasterMapper orderMasterMapper;

    @Test
    public void add() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456789");
        orderMaster.setBuyerName("张三");
        orderMaster.setBuyerPhone("13882111222");
        orderMaster.setBuyerAddress("北京");
        orderMaster.setBuyerOpenid("1232131213");
        orderMaster.setOrderAmount(new BigDecimal(12));
        orderMaster.setOrderStatus((byte)0);
        orderMaster.setPayStatus((byte)0);
        orderMasterMapper.add(orderMaster);
    }
}