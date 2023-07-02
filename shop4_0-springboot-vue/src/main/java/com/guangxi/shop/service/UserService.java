package com.guangxi.shop.service;

import com.guangxi.shop.entity.User;
import com.guangxi.shop.entity.UserInfo;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public interface UserService {
    //根据用户id获取用户
    public User getUserById(String Id);
    //根据用户名获取用户
    public User getUserByUsername(String userName);
    //添加一个用户
    public int addUser(String userName,String password,String email,String icon);
    //将用户信息添加至redis
    public String setUserInfoToRedisAndCookie(User user);
    //修改用户密码
    public int updatePwdById(String id,String newPwd);
    //退出登录
    public void logout(HttpServletRequest request);
    //通过id查找用户详情信息
    UserInfo getUserInfoById(String id);
}
