package com.guangxi.shop.service.impl;

import com.google.gson.Gson;
import com.guangxi.shop.entity.User;
import com.guangxi.shop.mapper.UserMapper;
import com.guangxi.shop.service.UserService;
import com.guangxi.shop.util.CookieUtil;
import com.guangxi.shop.util.DateTimeUtil;
import com.guangxi.shop.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.sql.Timestamp;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserByUsername(String userName) {
        User user = userMapper.getUserByUsername(userName);
        if(user!=null&&!"".equals(user.getId())){
            return user;
        }else {
            return null;
        }
    }

    @Override
    public int addUser(String userName, String password, String email, String icon) {
        //定义id字段
        String id = UUIDUtil.getIdByMyLength(10).toString();
        //定义创建修改时间
        Timestamp currentTime = DateTimeUtil.getCurrentTimeByTimestamp();
        //创建user
        User user = new User(id, userName, password, email, icon, currentTime, currentTime);
        //添加至数据库
        int result = userMapper.addUser(user);
        return result;
    }

    @Override
    public Cookie setUserInfoToRedisAndCookie( User user) {
        //创建token
        String token = UUIDUtil.getNumUUID();
        //将token保存至cookie中
        Cookie cookie = CookieUtil.setCookie("user", token);
        cookie.setMaxAge(600);
        //将user转换为json字符串
        Gson gson = new Gson();
        String userInfo = gson.toJson(user);
        //和redis创立链接
        Jedis jedis = new Jedis("127.0.0.1");
        //往redis中存放
        jedis.set(token,userInfo);
        //设置过期时间
        jedis.expire(token,600);
        return cookie;
    }

    @Override
    public int updatePwdById(String id, String newPwd) {
        //定义修改时间
        Timestamp currentTime = DateTimeUtil.getCurrentTimeByTimestamp();
        int result = userMapper.updatePwdById(id, newPwd, currentTime);
        return result;
    }

    @Override
    public void logout(HttpServletRequest request) {
        String token = null;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null&&cookies.length>0){
            for (Cookie cookie : cookies) {
                if("user".equals(cookie.getName())){
                    token = cookie.getValue();
                    cookie.setMaxAge(0);
                }
            }
        }
        Jedis jedis = new Jedis("127.0.0.1");
        jedis.del(token);
        request.getSession().invalidate();
    }
}
