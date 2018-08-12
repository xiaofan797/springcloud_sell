package com.xiaofan.sell.product.dao;

import com.xiaofan.sell.product.pojo.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {

    @Autowired
    ProductCategoryMapper productCategoryMapper;

    @Test
    public void findList() {
        List<ProductCategory> productCategoryList = productCategoryMapper.findList(new ProductCategory());
        System.out.println(productCategoryList);
    }
}