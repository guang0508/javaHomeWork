package com.guangxi.shop.servlet.user;

import com.guangxi.shop.dao.UserDao;
import com.guangxi.shop.dao.impl.UserDaoImpl;
import com.guangxi.shop.entity.User;
import com.guangxi.shop.utill.MysqlConnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@WebServlet(name = "RegisterServlet" ,value = "/register-servlet")
public class RegisterServlet extends HttpServlet {
    private UserDao userDao = new UserDaoImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userName = request.getParameter("userName");  //获取参数
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String icon = "https://edu-gx.oss-cn-beijing.aliyuncs.com/2022/06/01/b8d7943cd3a64be3b2d3c6c5e03b0086%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202022-05-31%20135807.jpg";

        //定义id字段
        String id = UUID.randomUUID().toString();
        //获取指定格式当前日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        //将日期进行类型转换=>Timestamp类型
        String dateString = dateFormat.format(date);
        Timestamp currentDate = Timestamp.valueOf(dateString);
        //将获取的数据保存至login表
        User user = new User(id, userName, password, email, icon, currentDate, currentDate);
        int result = userDao.addUser(user);
        if (result>0){
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }else {
            System.out.println("注册失败");
        }
    }
}
