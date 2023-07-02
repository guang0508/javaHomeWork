package com.guangxi.shop.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateTimeUtil {
    //定义创建修改时间
    public static Timestamp getCurrentTimeByTimestamp(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String dateString = dateFormat.format(date);
        Timestamp currentTime = Timestamp.valueOf(dateString);
        return currentTime;
    }

}
