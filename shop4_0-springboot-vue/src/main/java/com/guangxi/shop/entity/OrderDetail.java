package com.guangxi.shop.entity;

import java.sql.Timestamp;

public class OrderDetail {
    String id;
    String orderId;
    String productId;
    String specId;
    Timestamp createTime;
    Timestamp updateTime;

    public OrderDetail(String id, String orderId, String productId, String specId, Timestamp createTime, Timestamp updateTime) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.specId = specId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
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
}
