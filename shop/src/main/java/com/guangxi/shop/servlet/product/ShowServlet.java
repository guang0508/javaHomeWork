package com.guangxi.shop.servlet.product;

import com.guangxi.shop.dao.CategoryDao;
import com.guangxi.shop.dao.ProductDao;
import com.guangxi.shop.dao.impl.CategoryDaoImpl;
import com.guangxi.shop.dao.impl.ProductDaoImpl;
import com.guangxi.shop.entity.Category;
import com.guangxi.shop.entity.Product;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShowServlet" , value = "/show-servlet")
public class ShowServlet extends HttpServlet {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    private ProductDao productDao = new ProductDaoImpl();
    public List<String> thirdCategoryIdList = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        List<Category> categoryList = categoryDao.getCategoryByParentId(id);
        if(categoryList.size()>0){
            Category category = categoryList.get(0);
            int level = category.getLevel();
            if(level==2){
//                request.setAttribute("secondCategoryList",categoryList);
                request.getSession().setAttribute("secondCategoryList",categoryList);
                if(request.getSession().getAttribute("thirdCategoryList")!=null){
                    request.getSession().removeAttribute("thirdCategoryList");
                }

            }else if(level==3){
//                request.setAttribute("thirdCategoryList",categoryList);
                request.getSession().setAttribute("thirdCategoryList",categoryList);
            }
        }else{
            //TODO
            List<Product> productList = productDao.getProductByCategoryId(id);
            if (productList.size()==0){
                request.getSession().setAttribute("productList",null);
            }else{
                request.getSession().setAttribute("productList",productList);
            }
//            request.getSession().setAttribute("secondCategoryList",null);
//            request.getSession().setAttribute("thirdCategoryList",null);
        }


        request.getRequestDispatcher("home.jsp").forward(request,response);
    }


}

