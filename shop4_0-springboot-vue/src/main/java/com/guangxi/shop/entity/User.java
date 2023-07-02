package com.guangxi.shop.entity;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class User {
    private String id;
    private String userName;
    private String password;
    private String email;
    private String icon;
    private Timestamp createTime;
    private Timestamp updateTime;

    public User() {
    }

    public User(String id, String user_name, String password, String email, String icon, Timestamp create_time, Timestamp update_time) {
        this.id = id;
        this.userName = user_name;
        this.password = password;
        this.email = email;
        this.icon = icon;
        this.createTime = create_time;
        this.updateTime = update_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_name() {
        return userName;
    }

    public void setUser_name(String user_name) {
        this.userName = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Date getCreate_time() {
        return createTime;
    }

    public void setCreate_time(Timestamp create_time) {
        this.createTime = create_time;
    }

    public Date getUpdate_time() {
        return updateTime;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.updateTime = update_time;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", user_name='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", icon='" + icon + '\'' +
                ", create_time=" + createTime +
                ", update_time=" + updateTime +
                '}';
    }
}
