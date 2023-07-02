package com.guangxi.shop.entity;

import java.sql.Timestamp;
import java.util.List;

public class Category {
    private String id;
    private String name;
    private String parent_id;
    private int level;
    private int sort;
    private int is_deleted;
    private Timestamp create_time;
    private Timestamp update_time;

    private List<Category> children;

    public Category(String id, String name, String parent_id, int level, int sort, int is_deleted, Timestamp create_time, Timestamp update_time, List<Category> children) {
        this.id = id;
        this.name = name;
        this.parent_id = parent_id;
        this.level = level;
        this.sort = sort;
        this.is_deleted = is_deleted;
        this.create_time = create_time;
        this.update_time = update_time;
        this.children = children;
    }

    public Category() {
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

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
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

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parent_id='" + parent_id + '\'' +
                ", level=" + level +
                ", sort=" + sort +
                ", is_deleted=" + is_deleted +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", children=" + children +
                '}';
    }
}
