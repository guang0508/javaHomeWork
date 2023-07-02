package com.guangxi.shop.controller;

import com.guangxi.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping("/productController")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/home-servlet")
    protected String showHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productService.showCategory(request,response);
        //将页面发送到home
        return "home";
    }
    @RequestMapping("/show-servlet")
    protected String showProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productService.showProduct(request,response);
        //将页面发送到home
        return "home";
    }


}
