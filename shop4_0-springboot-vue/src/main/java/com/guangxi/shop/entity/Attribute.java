package com.guangxi.shop.entity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Attribute {
    private String id;
    private String productId;
    private String color;
    private String size;
    private int count;

    public Attribute(String id, String productId, String color, String size, int count) {
        this.id = id;
        this.productId = productId;
        this.color = color;
        this.size = size;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}