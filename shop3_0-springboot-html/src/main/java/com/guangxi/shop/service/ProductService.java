package com.guangxi.shop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ProductService {
    //首页展示分类
    public void showCategory(HttpServletRequest request, HttpServletResponse response);
    //首页展示商品
    public void showProduct(HttpServletRequest request, HttpServletResponse response);
}
