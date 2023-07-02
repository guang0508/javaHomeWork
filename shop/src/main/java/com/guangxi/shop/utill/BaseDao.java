package com.guangxi.shop.utill;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDao<T>{

    private Class entityClass;
    private PreparedStatement ps = null;
    private Connection connection = null;
    private ResultSet resultSet = null;

    private String url = "jdbc:mysql://localhost:3306/test_homework?useUnicode=true&characterEncoding=UTF-8";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String uname = "root";
    private String pwd = "010508";

    //构造方法创建
    public BaseDao(){
        Type genericType = getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
        Type actualType= actualTypeArguments[0];
        try {
            entityClass = Class.forName(actualType.getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取连接connection
    protected Connection getConnect(){
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,uname,pwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //关闭流
    protected void close(ResultSet result,PreparedStatement ps ,Connection connection){
        try {
            if(result!=null){
                result.close();
            }
            if(ps!=null){
                ps.close();
            }
            if(connection!=null){
                connection.close();;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //给sql语句设置参数
    protected void setParams(String sql,Object ...params) {
        if(params!=null&&params.length>0){
            for (int i = 0; i < params.length; i++) {
                try {
                    ps.setObject(i+1,params[i]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //更新操作
    protected int executeUpdate(String sql,Object ...params){
        int result = 0;
        try {
            connection = getConnect();
            ps = connection.prepareStatement(sql);
            setParams(sql,params);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(resultSet,ps,connection);
        }
        return result;
    }
    //查询操作(多个结果)
    protected List<T> executeQuery(String sql,Object ...params){
        List<T> list = new ArrayList<T>();
        try {
            connection = getConnect();
            ps = connection.prepareStatement(sql);
            setParams(sql,params);
            resultSet = ps.executeQuery(); //查询到结果集
            //到此步本可以直接返回，但为了后续操作更加简介，不再冗余，
            // 需要进一步解析结果集，使其返回能够直接使用的对象或对象集（和entity类相对应）
              //解析结果集resultSet
            ResultSetMetaData metaData = resultSet.getMetaData();//获取元数据（列名，表名，数据库名等）
            int columnCount = metaData.getColumnCount();//获取列的数量
            while(resultSet.next()){  //当结果集还有数据时
                T entity = (T)entityClass.newInstance();  //创建一个T类型的对象
                for (int i = 0; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i + 1);//根据索引，获取某列的列明
                    Object columnValue = resultSet.getObject(i + 1); //根据索引，获取列值
                    setValue(entity,columnName,columnValue);
                }
                list.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            close(resultSet,ps,connection);
        }
        return list;
    }
    //查询单个对象
    protected T executeAloneQuery(String sql,Object ...params){
        connection = getConnect();
        T entity = null ;
        try {
            ps = connection.prepareStatement(sql);
            setParams(sql,params);
            resultSet = ps.executeQuery();
            //分析结果集
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            //创建对象
            entity = (T)entityClass.newInstance();
            if(resultSet.next()){
                for (int i = 0; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i+1);
                    Object columnValue = resultSet.getObject(i + 1);
                    setValue(entity,columnName,columnValue);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            close(resultSet,ps,connection);
        }
        return entity;
    }

    //给类属性赋值
    protected void setValue(T entity, String columnName, Object columnValue) {
        Class clazz = entity.getClass();
        try {
            Field field = clazz.getDeclaredField(columnName);
            if(field!=null){
                field.setAccessible(true);
                field.set(entity,columnValue);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
