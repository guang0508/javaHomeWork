package com.guangxi.shop.mapper;

import com.guangxi.shop.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface ProductMapper {
    //根据分类id查询商品集合
    public List<Product> getProductByCategoryId(String categoryId);
    //根据销量获取所有商品
    public List<Product> getAllProductBySales();
    //根据关键字查找
    List<Product> getProductByKeyword(String keyword);
    //根据产品id查询产品
    Product getProductById(String productId);
    //根据商品规格查询价格
    BigDecimal getPriceBySpec(String productId, String attributeStr);
}
