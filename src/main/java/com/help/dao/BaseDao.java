package com.help.dao;
import java.sql.Connection;
import java.sql.DriverManager;


public class BaseDao {
    static String URL="jdbc:mysql://localhost:3306/help?serverTimezone=UTC&&useSSL=false&&characterEncoding=utf8";
    static String username="root";
    static String Password="root";
    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.toString()+"驱动加载失败");
        }
    }

    public Connection getConnection(){

        try {
            Connection connection= DriverManager.getConnection(URL,username, Password);
            return connection;
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return null;
    }
}
