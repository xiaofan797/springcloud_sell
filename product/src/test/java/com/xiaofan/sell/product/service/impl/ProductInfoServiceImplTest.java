package com.xiaofan.sell.product.service.impl;

import com.xiaofan.sell.product.dto.CartDTO;
import com.xiaofan.sell.product.service.ProductInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    ProductInfoService productInfoService;

    @Test
    public void decrStock() {
        productInfoService.decrStock(Arrays.asList(new CartDTO("164103465734242707",2)));
    }
}