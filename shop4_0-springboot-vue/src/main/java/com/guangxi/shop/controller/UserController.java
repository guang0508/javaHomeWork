package com.guangxi.shop.controller;


import com.google.gson.Gson;
import com.guangxi.shop.Result.R;
import com.guangxi.shop.entity.UpdatePwd;
import com.guangxi.shop.entity.User;
import com.guangxi.shop.entity.UserInfo;
import com.guangxi.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping("/userController")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/login-servlet")
    public R login(HttpServletRequest request, HttpServletResponse response,@RequestBody User userInfo) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        //通过cookie+token+redis实现单点登录
        String userName = userInfo.getUser_name();
        String password = userInfo.getPassword();
        //从数据库中获取信息.判断是否有该用户
        User user = userService.getUserByUsername(userName);
        if(user==null||"".equals(user.getId())){
            request.setAttribute("message","不存在该用户,请注册");
            return R.error().message("不存在该用户,请注册");
        }
        if(password!=null&&password.equals(user.getPassword())){
            String token = userService.setUserInfoToRedisAndCookie(user); //将用户token存在cookie，token为key将用户信息存到redis
            return R.ok().data("token",token);
        }else{
            request.setAttribute("message","密码错误");
            return R.error().message("密码错误");
        }
    }

    @RequestMapping("/register-servlet")
    protected R registerUser(HttpServletRequest request, HttpServletResponse response,@RequestBody User user) throws ServletException, IOException {
        String userName = user.getUser_name();  //获取参数
        String password = user.getPassword();
        String email = user.getEmail();
        String icon = "https://edu-gx.oss-cn-beijing.aliyuncs.com/2022/06/01/b8d7943cd3a64be3b2d3c6c5e03b0086%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202022-05-31%20135807.jpg";
        if("".equals(userName)||"".equals(password)){
            request.setAttribute("message","用户名或密码不能为空");
            return R.error().message("用户名或密码不能为空");
        }
        //查询是否已经注册
        User user1 = userService.getUserByUsername(userName);
        if(user1!=null&&!"".equals(user1.getId())){
            request.setAttribute("message","用户已注册");
            return R.error().message("用户已注册");
        }else{
            int result = userService.addUser(userName, password, email, icon);//添加用户
            if (result>0){
                return R.ok();
            }else {
                request.setAttribute("message","注册失败");
                return R.error().message("注册失败,稍后再试");
            }
        }
    }

    @RequestMapping("update-servlet")
    protected R updatePwd(HttpServletRequest request, HttpServletResponse response, @RequestBody UpdatePwd pwdInfo) throws ServletException, IOException {
        String beforePwd = pwdInfo.getOldPwd();
        String newPwd = pwdInfo.getNewPwd();
        String id = pwdInfo.getId();
        User user = userService.getUserById(id);
        String password = user.getPassword();
        if(!password.equals(beforePwd)){
            return R.error().message("旧密码输入错误");
        }
        if (beforePwd.equals(newPwd)){ //两次密码一致，无需修改
            request.setAttribute("message","新密码与旧密码一致，无需修改！");
            return R.error().message("新密码与旧密码一致，无需修改！");
        }
        if (("".equals(beforePwd)||beforePwd==null)||("".equals(newPwd)||newPwd==null)){ //两次密码一致，无需修改
            request.setAttribute("message","新密码或旧密码未填写");
            return R.error().message("新密码或旧密码未填写");
        }
        //根据用户id修改密码
        int result = userService.updatePwdById(id, newPwd);
        if(result>0){
            return R.ok();
        }else{
            request.setAttribute("message","修改失败");
            return R.error().message("修改失败");
        }
    }

    @RequestMapping("exit-servlet")
    public R exit(HttpServletRequest request,HttpServletResponse response){
        userService.logout(request);
        return R.ok();
    }

    @RequestMapping("getUserInfo/{id}")
    public R getUserInfo(@PathVariable("id") String id){
        UserInfo userInfo= userService.getUserInfoById(id);
        User user = userService.getUserById(id);
        if (userInfo!=null){
            return R.ok().data("userInfo",userInfo).data("user",user);
        }else {
            return R.error().message("获取用户详情失败");
        }
    }



}
