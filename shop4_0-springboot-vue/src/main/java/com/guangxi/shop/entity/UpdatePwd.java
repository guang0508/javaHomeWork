package com.guangxi.shop.entity;

public class UpdatePwd {
    String oldPwd;
    String newPwd;
    String id;

    public UpdatePwd(String oldPwd, String newPwd, String id) {
        this.oldPwd = oldPwd;
        this.newPwd = newPwd;
        this.id = id;
    }

    public UpdatePwd() {
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
