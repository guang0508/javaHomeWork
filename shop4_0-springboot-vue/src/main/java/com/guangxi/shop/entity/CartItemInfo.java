package com.guangxi.shop.entity;

import java.math.BigDecimal;

public class CartItemInfo {
    private String id;
    private String name;
    private String cover;
    private BigDecimal price;
    private int count;
    private String size;
    private String color ;
    private String shopName;

    public CartItemInfo(String id, String name, String cover, BigDecimal price, int count, String size, String color, String shopName) {
        this.id = id;
        this.name = name;
        this.cover = cover;
        this.price = price;
        this.count = count;
        this.size = size;
        this.color = color;
        this.shopName = shopName;
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", cover='" + cover + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", shopName='" + shopName + '\'' +
                '}';
    }
}
