package com.guangxi.shop.Result;


import java.util.HashMap;
import java.util.Map;

public class R {
    private boolean success;
    private Integer code;
    private String message;
    private Map<String,Object> data = new HashMap<>();

    private R(){}  //将构造方法私有化

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    //  执行成功
    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(Code.SUCCESS);
        r.setMessage("成功");
        return r;
    }
    //    执行失败
    public static R error(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(Code.ERROR);
        r.setMessage("失败");
        return r;
    }
    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    public R code(Integer code){
        this.setCode(code);
        return this;
    }
    public R message(String message){
        this.setMessage(message);
        return this;
    }
    //存放数据
    public R data(String key,Object value){
        this.data.put(key,value);
        return this;
    }

    public R data(Map<String,Object> data){
        this.setData(data);
        return this;
    }

}
