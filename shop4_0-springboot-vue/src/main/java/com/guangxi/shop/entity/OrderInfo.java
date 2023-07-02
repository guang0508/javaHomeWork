package com.guangxi.shop.entity;

import java.sql.Timestamp;
import java.util.List;

public class OrderInfo {

    String address;
    String name;
    String telephone;
    String remark;

    public OrderInfo(String address, String name, String telephone, String remark) {
        this.address = address;
        this.name = name;
        this.telephone = telephone;
        this.remark = remark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
