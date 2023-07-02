package com.guangxi.shop.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Product {
    private String id;
    private String  name;
    private BigDecimal price;
    private String category_id;
    private int sort;
    private int sales;
    private int status;
    private int is_deleted;
    private Timestamp create_time;
    private Timestamp update_time;

    public Product(String id, String name, BigDecimal price, String category_id, int sort, int sales, int status, int is_deleted, Timestamp create_time, Timestamp update_time) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category_id = category_id;
        this.sort = sort;
        this.sales = sales;
        this.status = status;
        this.is_deleted = is_deleted;
        this.create_time = create_time;
        this.update_time = update_time;
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
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
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
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category_id='" + category_id + '\'' +
                ", sort=" + sort +
                ", sales=" + sales +
                ", status=" + status +
                ", is_deleted=" + is_deleted +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
