package com.guangxi.shop.mapper;

import com.guangxi.shop.entity.Attribute;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttributeMapper {
    //添加规格
    public void addSpecs(Attribute attribute);
    //通过规格id查询规格
    Attribute getSpecsById(String id);
    //通过id删除规格
    int deleteSpecsById(String specsId);
}
