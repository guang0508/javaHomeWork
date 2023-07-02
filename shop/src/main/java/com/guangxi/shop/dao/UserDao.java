package com.guangxi.shop.dao;

import com.guangxi.shop.entity.User;

import java.sql.Timestamp;

public interface UserDao {
    //通过账号获取用户
    public User getUserByUsername(String username);
    //添加用户
    public int addUser(User user);
    //根据用户id修改密码
    public int updatePwdById(String id, String password, Timestamp updateTime);
}
