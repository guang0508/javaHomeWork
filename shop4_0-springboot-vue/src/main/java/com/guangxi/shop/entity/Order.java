package com.guangxi.shop.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Order {
    String id;
    String userId;
    String telephone;
    String address;
    String addressee;
    String remark;
    BigDecimal price;
    int isPaid;
    int isDelete;
    int isSend;
    Timestamp createTime;
    Timestamp updateTime;
    List<OrderItemInfo> orderItemInfoByOrderId;

    public Order(String id, String userId, String telephone, String address, String addressee, String remark, BigDecimal price, int isPaid, int isDelete, int isSend, Timestamp createTime, Timestamp updateTime) {
        this.id = id;
        this.userId = userId;
        this.telephone = telephone;
        this.address = address;
        this.addressee = addressee;
        this.remark = remark;
        this.price = price;
        this.isPaid = isPaid;
        this.isDelete = isDelete;
        this.isSend = isSend;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(int isPaid) {
        this.isPaid = isPaid;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getIsSend() {
        return isSend;
    }

    public void setIsSend(int isSend) {
        this.isSend = isSend;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public List<OrderItemInfo> getOrderItemInfoByOrderId() {
        return orderItemInfoByOrderId;
    }

    public void setOrderItemInfoByOrderId(List<OrderItemInfo> orderItemInfoByOrderId) {
        this.orderItemInfoByOrderId = orderItemInfoByOrderId;
    }
}
