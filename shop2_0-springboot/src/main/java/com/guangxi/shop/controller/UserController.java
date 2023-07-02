package com.guangxi.shop.controller;


import com.guangxi.shop.entity.User;
import com.guangxi.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping("/userController")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login-servlet")
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        //通过cookie+token+redis实现单点登录
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        //从数据库中获取信息.判断是否有该用户
        User user = userService.getUserByUsername(userName);
        if(user==null||"".equals(user.getId())){
            request.setAttribute("message","不存在该用户,请注册");
            return "login";
        }
        if(password!=null&&password.equals(user.getPassword())){
            Cookie cookie = userService.setUserInfoToRedisAndCookie(user); //将用户token存在cookie，token为key将用户信息存到redis
            cookie.setMaxAge(600);
            response.addCookie(cookie);
            return "redirect:/productController/home-servlet";
        }else{
            request.setAttribute("message","密码错误");
            return "login";
        }
    }

    @RequestMapping("/register-servlet")
    protected String registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userName = request.getParameter("userName");  //获取参数
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String icon = "https://edu-gx.oss-cn-beijing.aliyuncs.com/2022/06/01/b8d7943cd3a64be3b2d3c6c5e03b0086%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202022-05-31%20135807.jpg";
        if("".equals(userName)||"".equals(password)){
            request.setAttribute("message","用户名或密码不能为空");
            return "register";
        }
        //查询是否已经注册
        User user1 = userService.getUserByUsername(userName);
        if(user1!=null&&!"".equals(user1.getId())){
            request.setAttribute("message","用户已注册");
            return "register";
        }else{
            int result = userService.addUser(userName, password, email, icon);//添加用户
            if (result>0){
                return "login";
            }else {
                request.setAttribute("message","注册失败");
                return "register";
            }
        }
    }

    @RequestMapping("update-servlet")
    protected String updatePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String beforePwd = request.getParameter("beforePwd");
        String newPwd = request.getParameter("newPwd");
        if (beforePwd.equals(newPwd)){ //两次密码一致，无需修改
            request.setAttribute("message","新密码与旧密码一致，无需修改！");
            return "update";
        }
        User user = (User) request.getSession().getAttribute("user");
        //获取id
        String id = user.getId();
        //根据用户id修改密码
        int result = userService.updatePwdById(id, newPwd);
        if(result>0){
            return "login";
        }else{
            request.setAttribute("message","修改失败");
            return "register";
        }
    }

    @RequestMapping("exit-servlet")
    public String exit(HttpServletRequest request,HttpServletResponse response){
        userService.logout(request);
        return "redirect:/productController/home-servlet";
    }
}
