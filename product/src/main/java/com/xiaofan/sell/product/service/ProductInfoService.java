package com.xiaofan.sell.product.service;

import com.xiaofan.sell.api.pojo.ProductInfo;
import com.xiaofan.sell.product.dto.CartDTO;

import java.util.List;

public interface ProductInfoService {

    List<ProductInfo> findByTypes(List<Integer> typesList);

    List<ProductInfo> findByProductIds(List<String> productIdList);

    void decrStock(List<CartDTO> cartDTOList);
}
