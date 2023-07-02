package com.guangxi.shop.dao;

import com.guangxi.shop.entity.Category;

import java.util.List;

public interface CategoryDao {
    //根据父级分类id查询分类
    public List<Category> getCategoryByParentId(String parentId);
    //根据分类id查询分类
    public Category getCategoryById(String id);

}
