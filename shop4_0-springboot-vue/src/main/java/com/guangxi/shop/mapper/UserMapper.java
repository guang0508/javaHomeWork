package com.guangxi.shop.mapper;

import com.guangxi.shop.entity.User;
import com.guangxi.shop.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;

@Mapper
public interface UserMapper {
    //通过id获取用户
    public User getUserById(String Id);
    //通过账号获取用户
    public User getUserByUsername(String username);
    //添加用户
    public int addUser(User user);
    //根据用户id修改密码
    public int updatePwdById(@Param("id") String id, @Param("password") String password, @Param("updateTime") Timestamp updateTime);
    //根据用户id查找用户
    UserInfo getUserInfoById(String id);
}
