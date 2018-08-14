package com.xiaofan.sell.product.dao;

import com.xiaofan.sell.api.pojo.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoMapperTest {

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Test
    public void findList() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductStatus((byte)0);
        List<ProductInfo> productInfoList = productInfoMapper.findList(null,
                Arrays.asList("157875196366160022","157875227953464068"),productInfo);
        System.out.println(productInfoList);
    }
}