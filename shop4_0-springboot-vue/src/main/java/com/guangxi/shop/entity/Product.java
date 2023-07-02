package com.guangxi.shop.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Product {
    private String id;
    private String  name;
    private BigDecimal price;
    private String categoryId;
    private int sort;
    private int sales;
    private String cover;
    private String shopName;
    private int status;
    private int is_deleted;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String attributeList;

    public Product(String id, String name, BigDecimal price, String category_id, int sort, int sales, String cover, String shopName,int status, int is_deleted, Timestamp create_time, Timestamp update_time,String attributeList) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryId = category_id;
        this.sort = sort;
        this.sales = sales;
        this.cover = cover;
        this.shopName = shopName;
        this.status = status;
        this.is_deleted = is_deleted;
        this.createTime = create_time;
        this.updateTime = update_time;
        this.attributeList = attributeList;
    }

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory_id() {
        return categoryId;
    }

    public void setCategory_id(String category_id) {
        this.categoryId = category_id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(String attributeList) {
        this.attributeList = attributeList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category_id='" + categoryId + '\'' +
                ", sort=" + sort +
                ", sales=" + sales +
                ", cover='" + cover + '\'' +
                ", status=" + status +
                ", is_deleted=" + is_deleted +
                ", create_time=" + createTime +
                ", update_time=" + updateTime +
                ", shopName=" + shopName +
                '}';
    }
}
