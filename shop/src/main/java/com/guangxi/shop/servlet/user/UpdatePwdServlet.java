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

@WebServlet(name = "UpdatePwdServlet" , value = "/update-servlet")
public class UpdatePwdServlet extends HttpServlet {

    private UserDao userDao = new UserDaoImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String beforePwd = request.getParameter("beforePwd");
        String newPwd = request.getParameter("newPwd");
        if (beforePwd.equals(newPwd)){ //两次密码一致，无需修改
            request.setAttribute("message","新密码与旧密码一致，无需修改！");
            request.getRequestDispatcher("update.jsp").forward(request,response);
            System.out.println("密码一致");
            return;
        }
        User user = (User) request.getSession().getAttribute("user");
        //获取id
        String id = user.getId();
        //获取修改时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String dateString = dateFormat.format(date);
        Timestamp currentDate = Timestamp.valueOf(dateString);
        //根据用户id修改密码
        int result = userDao.updatePwdById(id, newPwd, currentDate);
        if(result>0){
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }else{
            System.out.println("修改失败");
        }
    }
}
