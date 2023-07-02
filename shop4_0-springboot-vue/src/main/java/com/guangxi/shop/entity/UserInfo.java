package com.guangxi.shop.entity;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class UserInfo {
    String uid;
    String address;
    String telephone;
    Timestamp birthday;
    Timestamp createTime;
    Timestamp updateTime;

    public UserInfo(String uid, String address, String telephone, Timestamp birthday, Timestamp create_time, Timestamp update_time) {
        this.uid = uid;
        this.address = address;
        this.telephone = telephone;
        this.birthday = birthday;
        this.createTime = create_time;
        this.updateTime = update_time;
    }

    public UserInfo() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public Timestamp getCreate_time() {
        return createTime;
    }

    public void setCreate_time(Timestamp create_time) {
        this.createTime = create_time;
    }

    public Timestamp getUpdate_time() {
        return updateTime;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.updateTime = update_time;
    }
}
