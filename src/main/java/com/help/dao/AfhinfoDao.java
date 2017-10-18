package com.help.dao;

import com.help.pojo.Afhinfo;
import com.help.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AfhinfoDao extends BaseDao {
    Connection connection = null;
    PreparedStatement statement = null;

    public void addAfhinfo(Afhinfo afhinfo) throws SQLException {

        String sql = "INSERT INTO `help`.`afhinfo` (  `userId`, `reqInfo`, `location`, `time`, `username`)  VALUES  (   ?  ,   ?   ,   ?   ,   ?   ,   ?   ) ";
        try {
            connection = getConnection();
            //建立传输SQL语句的statement
            statement = connection.prepareStatement(sql);

            statement.setString(1, String.valueOf(afhinfo.getUserId()));
            statement.setString(2, afhinfo.getReqInfo());
            statement.setString(3, afhinfo.getLocation());
            statement.setString(4, afhinfo.getTime());
            statement.setString(5, afhinfo.getUsername());

            //执行SQL，返回影响记录的行数
            int num = statement.executeUpdate();
            System.out.println("插入成功！"+"受影响的行数为："+num);
        } catch (SQLException e) {
            System.out.println("err="+e);
        }
        statement.close();
        connection.close();
    }
    public List<Afhinfo> SearchAfhinfo(int userId) throws SQLException {
        List<Afhinfo> afhinfos = null;
        afhinfos = new ArrayList<Afhinfo>();//不可少
        ResultSet resultSet = null;
        String sql = "SELECT   * FROM `help`.`afhinfo` WHERE `userId` like ? ";
        try {
            connection = getConnection();
            //建立传输SQL语句的statement
            statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(userId));
            //执行SQL，返回影响记录的行数
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Afhinfo info = new Afhinfo();
                info.setReqInfo(resultSet.getObject("reqInfo")==null? "" : resultSet.getString("reqInfo"));
                info.setLocation(resultSet.getObject("location")==null? "" : resultSet.getString("location"));//要判空！！！
                info.setTime(resultSet.getObject("time")==null? "" : resultSet.getString("time"));//要判空！！！
                info.setUsername(resultSet.getObject("username")==null? "" : resultSet.getString("username"));//要判空！！！
                info.setUserId(resultSet.getInt("userId"));//要判空！！！
                afhinfos.add(info);
            }
        } catch (SQLException e) {
            System.out.println("err="+e);
        }catch (NullPointerException e) {
            System.out.println("err="+e);
        }
        statement.close();
        connection.close();
        return afhinfos;
    }

}
