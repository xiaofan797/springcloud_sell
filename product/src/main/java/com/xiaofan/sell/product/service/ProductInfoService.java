package com.xiaofan.sell.product.service;

import com.xiaofan.sell.api.pojo.ProductInfo;

import java.util.List;

public interface ProductInfoService {

    List<ProductInfo> findByTypes(List<Integer> typesList);

    List<ProductInfo> findByProductIds(List<String> productIdList);
}
