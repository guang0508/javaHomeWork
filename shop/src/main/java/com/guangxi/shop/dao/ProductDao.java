package com.guangxi.shop.dao;

import com.guangxi.shop.entity.Product;

import java.util.List;

public interface ProductDao {
    //根据分类id查询商品集合
    public List<Product> getProductByCategoryId(String categoryId);
}
