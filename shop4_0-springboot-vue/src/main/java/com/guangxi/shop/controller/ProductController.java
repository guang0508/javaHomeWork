package com.guangxi.shop.controller;

import com.guangxi.shop.Result.R;
import com.guangxi.shop.entity.Attribute;
import com.guangxi.shop.entity.Category;
import com.guangxi.shop.entity.Product;
import com.guangxi.shop.service.ProductService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/productController")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/home-servlet")
    protected R showHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        R result = productService.showCategory(request, response);
        return result;
    }
    @RequestMapping("/selectProduct/{keyword}")
    protected R selectProduct(@PathVariable String keyword) {
        List<Product> productList = productService.selectProductByKeyword(keyword);
        if (productList!=null){
            return R.ok().data("productList",productList);
        }else {
            return R.error().message("未查到符合的所属商品");
        }
    }

    @RequestMapping("/productInfo/{productId}")
    protected R productInfo(@PathVariable String productId) {
        Product product = productService.selectProductById(productId);
        if (product!=null){
            return R.ok().data("product",product);
        }else {
            return R.error().message("未查到符合的所属商品");
        }
    }
//    @RequestMapping("/getNewPrice/{productId}")
//    protected R getNewPrice(@PathVariable String productId, @RequestBody List<Attribute> attributeList) {
//        BigDecimal newPrice = productService.getPriceBySpec(productId,attributeList);
//        if (newPrice!=null){
//            return R.ok().data("newPrice",newPrice);
//        }else {
//            return R.error();
//        }
//    }

    @RequestMapping("/ProductByCategory/{id}")
    protected R selectProductByCategory(@PathVariable String id) {
        List<Product> productList = productService.selectProductByCategory(id);
        if (productList!=null){
            return R.ok().data("productList",productList);
        }else {
            return R.error().message("该分类暂无商品上架哦");
        }
    }

    @RequestMapping("/getProductDetail/{productId}")
    protected R getProductDetail(@PathVariable String productId) {
        Product product = productService.selectProductById(productId);
        if (product!=null){
            return R.ok().data("product",product);
        }else {
            return R.error().message("获取失败，请稍后重试!");
        }
    }




}
