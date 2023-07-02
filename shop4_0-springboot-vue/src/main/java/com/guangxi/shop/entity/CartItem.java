package com.guangxi.shop.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CartItem {
    private String id;
    private String userId;
    private String productId;
    private BigDecimal price;
    private String specsId;
    private Timestamp createTime;
    private Timestamp updateTime;

    public CartItem(String id, String userId, String productId, BigDecimal price, String specsId, Timestamp createTime, Timestamp updateTime) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.price = price;
        this.specsId = specsId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public CartItem() {
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public String getSpecsId() {
        return specsId;
    }

    public void setSpecsId(String specsId) {
        this.specsId = specsId;
    }
}
