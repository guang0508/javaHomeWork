package com.guangxi.shop.servlet.user;

import com.google.gson.Gson;
import com.guangxi.shop.dao.UserDao;
import com.guangxi.shop.dao.impl.UserDaoImpl;
import com.guangxi.shop.entity.User;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "LoginServlet" ,value = "/login-servlet")
public class LoginServlet extends HttpServlet {

    private UserDao userDao = new UserDaoImpl();
    //请求打到doPost方法
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //通过cookie+token+redis实现单点登录
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String token = null;
        //从数据库中获取信息.判断是否有该用户
        User user = userDao.getUserByUsername(userName);
        if(user.getId()==null||"".equals(user.getId())){
            request.setAttribute("message","不存在该用户,请注册");
            request.getRequestDispatcher("login.jsp").forward(request,response);
            System.out.println("不存在该用户");
            return;
        }
        if(password!=null&&password.equals(user.getPassword())){
            //获取token （key）
            token = UUID.randomUUID().toString().replaceAll("-","");
            //将user对象转换为json字符串（value）
            Gson gson = new Gson();
            String userJson = gson.toJson(user);
            //将userJson保存至redis中
            Jedis jedis = new Jedis("127.0.0.1");
            jedis.set(token,userJson);
            jedis.expire(token,600); //设置过期时间10分钟
            Cookie cookie = new Cookie("user",token); //将token存入cookie中
            response.addCookie(cookie);
            response.sendRedirect("home-servlet");
        }else{
            request.setAttribute("message","密码错误");
            request.getRequestDispatcher("login.jsp").forward(request,response);
            System.out.println("密码错误");
        }
    }
}
