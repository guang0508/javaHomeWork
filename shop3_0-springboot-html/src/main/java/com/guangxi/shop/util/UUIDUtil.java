package com.guangxi.shop.util;

import java.util.UUID;

public class UUIDUtil {
    //初始uuid
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
    //获取没用”-“的uuid
    public static String getNumUUID(){
        String uuid = UUID.randomUUID().toString().replace("-","");
        return uuid;
    }
    //获取指定长度的uuid
    public static StringBuffer getIdByMyLength(int length){
        String id = UUID.randomUUID().toString().replace("-", "");
        char[] chars = id.toCharArray();
        StringBuffer result = new StringBuffer();
        for(int i = 0 ; i < length ; i++){
            result.append(chars[i]);
        }
        return result;
    }

}
