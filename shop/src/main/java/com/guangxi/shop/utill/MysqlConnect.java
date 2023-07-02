package com.guangxi.shop.utill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnect {
    public static Connection getConnect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/test_homework?useUnicode=true&characterEncoding=UTF-8";
        String driver = "com.mysql.cj.jdbc.Driver";
        String uname = "root";
        String pwd = "010508";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(url, uname, pwd);
        return connection;
    }
}
