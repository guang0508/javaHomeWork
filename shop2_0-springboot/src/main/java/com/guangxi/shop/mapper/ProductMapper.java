package com.guangxi.shop.mapper;

import com.guangxi.shop.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    //根据分类id查询商品集合
    public List<Product> getProductByCategoryId(String categoryId);
}
