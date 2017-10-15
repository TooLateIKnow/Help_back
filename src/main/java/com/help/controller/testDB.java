package com.help.controller;

import com.help.dao.UserDao;
import com.help.pojo.User;

import java.sql.SQLException;

public class testDB {
    User user1;

    Boolean addUser(){
        user1 = new User();
        user1.setUsername("root");
        user1.setPassword("root");
        user1.setMobilephone("17854212890");
        user1.setUsermail("282507860@qq.com");
        user1.setUsersex("female");
        UserDao userDao = new UserDao();
        try {
            userDao.AddUser(user1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public void init(){
        System.out.println("成功");
    }
}
