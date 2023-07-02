package com.guangxi.shop.dao.impl;

import com.guangxi.shop.dao.ProductDao;
import com.guangxi.shop.entity.Product;
import com.guangxi.shop.utill.BaseDao;

import java.util.List;

public class ProductDaoImpl extends BaseDao<Product> implements ProductDao {
    @Override
    public List<Product> getProductByCategoryId(String categoryId) {
        String sql = "select * from tb_product where category_id=?";
        return super.executeQuery(sql,categoryId);
    }
}
