package com.guangxi.shop.entity;

import java.sql.Timestamp;
import java.util.List;

public class Category {
    private String id;
    private String name;
    private String parentId;
    private int level;
    private int sort;
    private int is_deleted;
    private Timestamp createTime;
    private Timestamp updateTime;

    private List<Category> children;

    public Category(String id, String name, String parent_id, int level, int sort, int is_deleted, Timestamp create_time, Timestamp update_time, List<Category> children) {
        this.id = id;
        this.name = name;
        this.parentId = parent_id;
        this.level = level;
        this.sort = sort;
        this.is_deleted = is_deleted;
        this.createTime = create_time;
        this.updateTime = update_time;
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
        return parentId;
    }

    public void setParent_id(String parent_id) {
        this.parentId = parent_id;
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
                ", parent_id='" + parentId + '\'' +
                ", level=" + level +
                ", sort=" + sort +
                ", is_deleted=" + is_deleted +
                ", create_time=" + createTime +
                ", update_time=" + updateTime +
                ", children=" + children +
                '}';
    }
}
