package com.guangxi.shop.mapper;

import com.guangxi.shop.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    //根据父级分类id查询分类
    public List<Category> getCategoryByParentId(String parentId);
    //根据分类id查询分类
    public Category getCategoryById(String id);
}
