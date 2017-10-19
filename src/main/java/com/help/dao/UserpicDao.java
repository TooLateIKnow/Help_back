package com.help.dao;

import com.help.pojo.Helpinfo;
import com.help.pojo.User;
import com.help.pojo.Userpic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserpicDao extends BaseDao {
    Connection connection = null;
    PreparedStatement statement = null;
    public void addUserpic(Userpic userpic) throws SQLException {

        String sql = "INSERT INTO `help`.`userpic` (  `userId`, `picnum`)  VALUES  (   ?  ,   ?   ) ";
        try {
            connection = getConnection();
            //建立传输SQL语句的statement
            statement = connection.prepareStatement(sql);

            statement.setString(1, String.valueOf(userpic.getUserId()));
            statement.setString(2, userpic.getPicnum());


            //执行SQL，返回影响记录的行数
            int num = statement.executeUpdate();
            System.out.println("插入成功！"+"受影响的行数为："+num);
        } catch (SQLException e) {
            System.out.println("err="+e);
        }
        statement.close();
        connection.close();
    }
    public String searchUserpic(int userID) throws SQLException{
        Userpic userpic = new Userpic();
        String sql = "SELECT   * FROM `help`.`userpic` WHERE `userId` like ? ";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(userID));
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                userpic.setPicnum(resultSet.getObject("picnum")==null? "" : resultSet.getString("picnum"));
                userpic.setUserId(resultSet.getInt("userId"));
                //System.out.println("查询成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        statement.close();
        connection.close();
        return userpic.getPicnum();
    }
}
