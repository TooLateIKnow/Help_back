package com.help.dao;

import com.help.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends BaseDao{
    Connection connection = null;
    PreparedStatement statement = null;
    List<User> lrs = null;

    public  int Search(User user) throws SQLException {
        String username = user.getUsername();//定义用户名
        String mobilephone = user.getMobilephone();//定义手机
        String usermail = user.getUsermail();//定义邮箱
        String usersex = user.getUsersex();//定义性别
        String password = user.getPassword();//定义密码
        String sql = "SELECT   `userId` FROM `help`.`userinfo` WHERE `mobilephone` like ? ";
//        lrs = new ArrayList<User>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, mobilephone);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                System.out.println("已注册");
                statement.close();
                connection.close();
                return 1;
            }
            else{
                System.out.println("未注册");
                statement.close();
                connection.close();
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 2;
    }
    public void AddUser(User user) throws SQLException {
        String sql = "INSERT INTO `help`.`userinfo` (  `username`, `mobilephone`, `usermail`, `usersex`, `password`)  VALUES  (   ?  ,   ?   ,   ?   ,   ?   ,   ?   ) ";
        try {
            connection = getConnection();
            //建立传输SQL语句的statement
            statement = connection.prepareStatement(sql);

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getMobilephone());
            statement.setString(3, user.getUsermail());
            statement.setString(4, user.getUsersex());
            statement.setString(5, user.getPassword());

            //执行SQL，返回影响记录的行数
            int num = statement.executeUpdate();
            System.out.println("插入成功！"+"受影响的行数为："+num);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("err="+e);
        }
        statement.close();
        connection.close();
    }
    public  User SearchPassword(String phoneNum) throws SQLException{
        User user = new User();
        String sql = "SELECT   * FROM `help`.`userinfo` WHERE `mobilephone` like ? ";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, phoneNum);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                user.setUsername(resultSet.getString("username"));
                user.setUserId(resultSet.getInt("userId"));
                user.setPassword(resultSet.getString("password"));
                user.setMobilephone(resultSet.getString("mobilephone"));
                user.setUsermail(resultSet.getString("usermail"));
                user.setUsersex(resultSet.getString("usersex"));
            }
            else {
                user.setUserId(0000);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        statement.close();
        connection.close();
        return user;
    }
    public int changeMobilephone(User user) throws SQLException{
        int num = 0;
        String id = String.valueOf(user.getUserId());
        String sql = "UPDATE `help`.`userinfo` SET `mobilephone` = ? WHERE `userId` like ? ";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,user.getMobilephone());
            statement.setString(2, id);
            num = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        statement.close();
        connection.close();
        return num;
    }
    public int changeUsermail(User user) throws SQLException{
        int num = 0;
        String id = String.valueOf(user.getUserId());
        String sql = "UPDATE `help`.`userinfo` SET `usermail` = ? WHERE `userId` like ? ";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,user.getUsermail());
            statement.setString(2, id);
            num = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        statement.close();
        connection.close();
        return num;
    }
    public int changeUsername(User user) throws SQLException {
        int num = 0;
        String id = String.valueOf(user.getUserId());
        String sql = "UPDATE `help`.`userinfo` SET `username` = ? WHERE `userId` like ? ";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,user.getUsername());
            statement.setString(2, id);
            num = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        statement.close();
        connection.close();
        return num;
    }
}

