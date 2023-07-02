package com.guangxi.shop.servlet.product;

import com.google.gson.Gson;
import com.guangxi.shop.dao.CategoryDao;
import com.guangxi.shop.dao.impl.CategoryDaoImpl;
import com.guangxi.shop.entity.Category;
import com.guangxi.shop.entity.User;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HomeServlet",value = "/home-servlet")
public class HomeServlet extends HttpServlet {

    private CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                        request.getSession().setAttribute("user",user); //将user保存至session
                    }
                }
            }
        }
        //主页其他业务逻辑编写
          //获取树形分类
        List<Category> treeCategoryList = getTreeCategoryList();
        request.getSession().setAttribute("treeCategoryList",treeCategoryList);
        request.getSession().setAttribute("secondCategoryList",null);
        request.getSession().setAttribute("thirdCategoryList",null);
        //将页面发送到home
        request.getRequestDispatcher("home.jsp").forward(request,response);
    }
    public List<Category> getTreeCategoryList(){
        //获取所有的商品分类（树形结构）
        List<Category> resultList = new ArrayList<>();
        List<Category> firstLevelCategoryList = categoryDao.getCategoryByParentId("0");
        for (Category category : firstLevelCategoryList) {
            //给子分类属性进行赋值
            Category treeCategory = setChildrenCategory(category);
            resultList.add(treeCategory);
        }
        return resultList;
    }
    public Category setChildrenCategory(Category parentCategory){
        List<Category> childrenCategoryList = categoryDao.getCategoryByParentId(parentCategory.getId());
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
}
