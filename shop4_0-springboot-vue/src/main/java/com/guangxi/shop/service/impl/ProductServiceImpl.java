package com.guangxi.shop.service.impl;

import com.google.gson.Gson;
import com.guangxi.shop.Result.R;
import com.guangxi.shop.entity.Attribute;
import com.guangxi.shop.entity.Category;
import com.guangxi.shop.entity.Product;
import com.guangxi.shop.entity.User;
import com.guangxi.shop.mapper.CategoryMapper;
import com.guangxi.shop.mapper.ProductMapper;
import com.guangxi.shop.service.ProductService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;
    private ModelAndView mav = new ModelAndView();

    public R showCategory(HttpServletRequest request, HttpServletResponse response) {
        String userInfo = null;
        String token = request.getHeader("token");
        User user = null;
                if(token!=null){
                    Jedis jedis = new Jedis("127.0.0.1");
                    userInfo = jedis.get(token);
                    if(userInfo!=null&&!"".equals(userInfo)){
                        Gson gson = new Gson();       //将从redis获取的到json字符串转换为user对象
                        user = gson.fromJson(userInfo, User.class);
                    }
        }
        //主页其他业务逻辑编写
        List<Product> allProductList = productMapper.getAllProductBySales();
        //获取树形分类
        List<Category> treeCategoryList = getCategory();
        return R.ok().data("user",user).data("allProductList",allProductList).data("treeCategoryList",treeCategoryList);
    }
    @Override
    public List<Product> selectProductByKeyword(String keyword) {
        List<Product> productList = productMapper.getProductByKeyword(keyword);
        return productList;
    }

    @Override
    public List<Product> selectProductByCategory(String id) {
        List<Product> productList = productMapper.getProductByCategoryId(id);
        return productList;
    }

    @Override
    public List<Category> getCategory() {
        List<Category> firstLevelList = categoryMapper.getCategoryByParentId("0");
        List<Category> treeCategoryList = new ArrayList<>();
        for (Category category : firstLevelList) {
           Category treeCategory = setChildren(category,category.getId());
           treeCategoryList.add(treeCategory);
        }
        return treeCategoryList;
    }

    @Override
    public Product selectProductById(String id) {
        Product product = productMapper.getProductById(id);
        return product;
    }

//    @Override
//    public BigDecimal getPriceBySpec(String productId, List<Attribute> attributeList) {
//        Map<String,List<String>> map = new HashMap<>();
//        for (Attribute attribute : attributeList) {
//            String key = attribute.getKey();
//            List<String> value = attribute.getValue();
//            map.put(key, value);
//        }
//        Gson gson = new Gson();
//        String attributeStr = gson.toJson(map);
//        BigDecimal newPrice = productMapper.getPriceBySpec(productId,attributeStr);
//        return newPrice;
//    }

    public Category setChildren(Category parCategory, String id) {
        List<Category> childrenLevelList = categoryMapper.getCategoryByParentId(id);
        List<Category> categoryList = new ArrayList<>();
        if(childrenLevelList==null){
            return null;
        }
        for (Category category : childrenLevelList) {
            Category categoryTemp = setChildren(category,category.getId());
            categoryList.add(categoryTemp);
        }
        parCategory.setChildren(categoryList);
        return parCategory;
    }

}
