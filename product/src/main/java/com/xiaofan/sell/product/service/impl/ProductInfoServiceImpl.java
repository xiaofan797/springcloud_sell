package com.xiaofan.sell.product.service.impl;

import com.xiaofan.sell.product.dao.ProductInfoMapper;
import com.xiaofan.sell.product.enums.ProductStatus;
import com.xiaofan.sell.product.pojo.ProductInfo;
import com.xiaofan.sell.product.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> findByTypes(List<Integer> typesList) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductStatus(ProductStatus.UP.getCode());
        return productInfoMapper.findByTypes(typesList,productInfo);
    }
}
