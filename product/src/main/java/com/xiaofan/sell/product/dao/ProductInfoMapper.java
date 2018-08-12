package com.xiaofan.sell.product.dao;

import com.xiaofan.sell.product.pojo.ProductInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductInfoMapper {
    int deleteByPrimaryKey(String productId);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    ProductInfo findById(String productId);

    List<ProductInfo> findByTypes(@Param("typesList") List<Integer> typesList,@Param("product")ProductInfo productInfo);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);
}