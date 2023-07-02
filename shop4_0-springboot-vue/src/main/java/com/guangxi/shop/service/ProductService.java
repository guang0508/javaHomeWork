package com.guangxi.shop.service;

import com.guangxi.shop.Result.R;
import com.guangxi.shop.entity.Attribute;
import com.guangxi.shop.entity.Category;
import com.guangxi.shop.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    //首页展示分类
    public R showCategory(HttpServletRequest request, HttpServletResponse response);
    //通过关键字查找
    List<Product> selectProductByKeyword(String keyword);
    //通过分类查找
    List<Product> selectProductByCategory(String id);
    //查找一级分类
    List<Category> getCategory();
    //通过Id查找商品
    Product selectProductById(String Id);
    //通过商品id和规格进行价格的更新
    //BigDecimal getPriceBySpec(String productId, List<Attribute> attributeList);
}
