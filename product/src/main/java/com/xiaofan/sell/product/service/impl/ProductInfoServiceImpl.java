package com.xiaofan.sell.product.service.impl;

import com.xiaofan.sell.api.pojo.ProductInfo;
import com.xiaofan.sell.product.dao.ProductInfoMapper;
import com.xiaofan.sell.product.dto.CartDTO;
import com.xiaofan.sell.product.enums.ProductStatusEnum;
import com.xiaofan.sell.product.enums.ResultEnum;
import com.xiaofan.sell.product.exception.ProductException;
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
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return productInfoMapper.findList(typesList,null,productInfo);
    }

    @Override
    public List<ProductInfo> findByProductIds(List<String> productIdList) {
        return productInfoMapper.findList(null,productIdList,new ProductInfo());
    }

    @Override
    public void decrStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = productInfoMapper.findById(cartDTO.getProductId());
            if(productInfo==null){//商品不存在
                throw  new ProductException(ResultEnum.PRODUCT_NOT_EXIST.getCode(),
                        ResultEnum.PRODUCT_NOT_EXIST.getMessage());
            }
            Integer nowStock = productInfo.getProductStock()-cartDTO.getProductQuantity();
            if (nowStock<0){
                throw  new ProductException(ResultEnum.STOCK_ERROR.getCode(),
                        ResultEnum.STOCK_ERROR.getMessage());
            }
            productInfo.setProductStock(nowStock);
            productInfoMapper.update(productInfo);
        }
    }
}
