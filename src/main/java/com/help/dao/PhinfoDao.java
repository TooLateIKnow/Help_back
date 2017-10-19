package com.help.dao;

import com.help.pojo.Afhinfo;
import com.help.pojo.Phinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhinfoDao extends BaseDao {
//    private String userId;
//    private String prohelpInfo;
//    private String AFHuserId;
//    private String time;
//    private String location;
    Connection connection = null;
    PreparedStatement statement = null;
    public void addAfhinfo(Phinfo phinfo) throws SQLException {

        String sql = "INSERT INTO `help`.`phinfo` (  `userId`, `prohelpInfo`, `AFHuserId`, `time`, `location`)  VALUES  (   ?  ,   ?   ,   ?   ,   ?   ,   ?   ) ";
        try {
            connection = getConnection();
            //建立传输SQL语句的statement
            statement = connection.prepareStatement(sql);

            statement.setString(1, String.valueOf(phinfo.getUserId()));
            statement.setString(2, phinfo.getProhelpInfo());
            statement.setString(3, String.valueOf(phinfo.getAFHuserId()));
            statement.setString(4, phinfo.getTime());
            statement.setString(5, phinfo.getLocation());


            //执行SQL，返回影响记录的行数
            int num = statement.executeUpdate();
            System.out.println("插入成功！"+"受影响的行数为："+num);
        } catch (SQLException e) {
            System.out.println("err="+e);
        }
        statement.close();
        connection.close();
    }
    public List<Phinfo> SearchAfhinfo(int userId) throws SQLException {
        List<Phinfo> phinfoList = null;
        phinfoList = new ArrayList<Phinfo>();//不可少
        ResultSet resultSet = null;
        String sql = "SELECT   * FROM `help`.`phinfo` WHERE `userId` like ? ";
        try {
            connection = getConnection();
            //建立传输SQL语句的statement
            statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(userId));
            //执行SQL，返回影响记录的行数
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Phinfo info = new Phinfo();

                info.setUserId(resultSet.getInt("userId"));
                info.setAFHuserId(resultSet.getInt("AFHuserId"));
                info.setProhelpInfo(resultSet.getObject("prohelpInfo")==null? "" : resultSet.getString("prohelpInfo"));
                info.setLocation(resultSet.getObject("location")==null? "" : resultSet.getString("location"));//要判空！！！
                info.setTime(resultSet.getObject("time")==null? "" : resultSet.getString("time"));//要判空！！！

                phinfoList.add(info);
            }
        } catch (SQLException e) {
            System.out.println("err="+e);
        }catch (NullPointerException e) {
            System.out.println("err="+e);
        }
        statement.close();
        connection.close();
        return phinfoList;
    }

}
