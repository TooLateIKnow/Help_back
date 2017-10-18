package com.help.dao;

import com.help.pojo.Afhinfo;
import com.help.pojo.Helpinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HelpinfoDao extends BaseDao {
    Connection connection = null;
    PreparedStatement statement = null;

public List<Helpinfo> SearchHelpinfo(int userId) throws SQLException {
    List<Helpinfo> helpinfos = null;
    helpinfos = new ArrayList<Helpinfo>();//不可少
    ResultSet resultSet = null;
    String sql = "SELECT   * FROM `help`.`helpinfo` WHERE `userId` like ? ";
    try {
        connection = getConnection();
        //建立传输SQL语句的statement
        statement = connection.prepareStatement(sql);
        statement.setString(1, String.valueOf(userId));
        //执行SQL，返回影响记录的行数
        resultSet = statement.executeQuery();
        while (resultSet.next()){
            //    private String userAFHId;
//    private String userPHId;
//    private String location;
//    private String time;
//    private String helpWhat;
            Helpinfo info = new Helpinfo();
            info.setUserAFHId(resultSet.getObject("reqInfo")==null? "" : resultSet.getString("reqInfo"));
            info.setLocation(resultSet.getObject("location")==null? "" : resultSet.getString("location"));//要判空！！！
            info.setTime(resultSet.getObject("time")==null? "" : resultSet.getString("time"));//要判空！！！
            info.setUserPHId(resultSet.getObject("username")==null? "" : resultSet.getString("username"));//要判空！！！
            info.setHelpWhat(resultSet.getObject("username")==null? "" : resultSet.getString("username"));//要判空！！！
            helpinfos.add(info);
        }
    } catch (SQLException e) {
        System.out.println("err="+e);
    }catch (NullPointerException e) {
        System.out.println("err="+e);
    }
    statement.close();
    connection.close();
    return helpinfos;
}
}
