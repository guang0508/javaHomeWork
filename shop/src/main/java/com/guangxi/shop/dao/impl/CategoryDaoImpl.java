package com.guangxi.shop.dao.impl;

import com.guangxi.shop.dao.CategoryDao;
import com.guangxi.shop.entity.Category;
import com.guangxi.shop.utill.BaseDao;

import java.util.List;

public class CategoryDaoImpl extends BaseDao<Category> implements CategoryDao {
    @Override
    public List<Category> getCategoryByParentId(String parentId) {
        String sql = "select * from tb_category where parent_id = ?";
        return super.executeQuery(sql,parentId);
    }

    @Override
    public Category getCategoryById(String id) {
        String sql = "select * from tb_category where id = ?";
        return super.executeAloneQuery(sql,id);
    }
}
