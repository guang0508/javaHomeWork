package com.guangxi.shop.dao.impl;

import com.guangxi.shop.dao.UserDao;
import com.guangxi.shop.entity.User;
import com.guangxi.shop.utill.BaseDao;

import java.sql.Timestamp;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    @Override
    public User getUserByUsername(String username) {
        String sql = "select * from tb_login where user_name = ?";
        return super.executeAloneQuery(sql,username);
    }

    @Override
    public int addUser(User user) {
        String sql = "insert into tb_login values(?,?,?,?,?,?,?)";
        return super.executeUpdate(sql,user.getId(),user.getUserName(),user.getPassword(),user.getEmail(),
                user.getIcon(),user.getCreate_time(),user.getUpdate_time());
    }

    @Override
    public int updatePwdById(String id, String password, Timestamp updateTime) {
        String sql = "update tb_login set password = ? , update_time = ? where id = ?";
        return super.executeUpdate(sql,password,updateTime,id);
    }
}
