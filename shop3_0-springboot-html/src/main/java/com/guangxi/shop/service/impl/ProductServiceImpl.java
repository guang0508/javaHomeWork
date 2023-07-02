package com.guangxi.shop.service.impl;

import com.google.gson.Gson;
import com.guangxi.shop.entity.Category;
import com.guangxi.shop.entity.Product;
import com.guangxi.shop.entity.User;
import com.guangxi.shop.mapper.CategoryMapper;
import com.guangxi.shop.mapper.ProductMapper;
import com.guangxi.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;
    private ModelAndView mav = new ModelAndView();

    @Override
    public void showCategory(HttpServletRequest request, HttpServletResponse response) {
        String userInfo = null;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if("user".equals(cookie.getName())){
                    Jedis jedis = new Jedis("127.0.0.1");
                    userInfo = jedis.get(cookie.getValue());
                    if(userInfo!=null&&!"".equals(userInfo)){
                        Gson gson = new Gson();       //将从redis获取的到json字符串转换为user对象
                        User user = gson.fromJson(userInfo, User.class);
                        request.getSession().setAttribute("user",user); //将userName保存至session
                    }else{
                        request.getSession().setAttribute("user",null);
                    }
                }
            }
        }
        //主页其他业务逻辑编写
        List<Product> allProductList = productMapper.getAllProductBySales();
        //获取树形分类
        List<Category> treeCategoryList = getTreeCategoryList();
        request.getSession().setAttribute("allProductList",allProductList);
        request.getSession().setAttribute("treeCategoryList",treeCategoryList);
        request.getSession().setAttribute("secondCategoryList",null);
        request.getSession().setAttribute("thirdCategoryList",null);
        request.getSession().setAttribute("productList",null);
    }

    public List<Category> getTreeCategoryList(){
        //获取所有的商品分类（树形结构）
        List<Category> resultList = new ArrayList<>();
        List<Category> firstLevelCategoryList = categoryMapper.getCategoryByParentId("0");
        for (Category category : firstLevelCategoryList) {
            //给子分类属性进行赋值
            Category treeCategory = setChildrenCategory(category);
            resultList.add(treeCategory);
        }
        return resultList;
    }
    public Category setChildrenCategory(Category parentCategory){
        List<Category> childrenCategoryList = categoryMapper.getCategoryByParentId(parentCategory.getId());
        List<Category> childrenList = new ArrayList<>();
        if(childrenCategoryList == null){
            return parentCategory;
        }
        for (Category childrenCategory : childrenCategoryList) {
            Category category = setChildrenCategory(childrenCategory);
            childrenList.add(category);
        }
        parentCategory.setChildren(childrenList);
        return parentCategory;
    }

    @Override
    public void showProduct(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        List<Category> categoryList = categoryMapper.getCategoryByParentId(id);
        if(categoryList.size()>0){
            Category category = categoryList.get(0);
            int level = category.getLevel();
            if(level==2){
                request.getSession().setAttribute("secondCategoryList",categoryList);
                if(request.getSession().getAttribute("thirdCategoryList")!=null){
                    request.getSession().removeAttribute("thirdCategoryList");
                }

            }else if(level==3){
                request.getSession().setAttribute("thirdCategoryList",categoryList);
            }
        }else{
            List<Product> productList = productMapper.getProductByCategoryId(id);
            if (productList.size()==0){
                request.getSession().setAttribute("productList",null);
            }else{
                request.getSession().setAttribute("productList",productList);
            }
        }
    }
}
