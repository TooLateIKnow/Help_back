package com.help.dao;

import com.help.pojo.Afhinfo;
import com.help.pojo.Helpinfo;
import com.help.pojo.ShowHistory;
import com.help.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HelpinfoDao extends BaseDao {
    Connection connection = null;
    PreparedStatement statement = null;

    public void addHelpinfo(Helpinfo helpinfo) throws SQLException {

        String sql = "INSERT INTO `help`.`helpinfo` (  `userAFHId`, `userPHId`, `location`, `time`, `helpWhat`)  VALUES  (   ?  ,   ?   ,   ?   ,   ?   ,   ?   ) ";
        try {
            connection = getConnection();
            //建立传输SQL语句的statement
            statement = connection.prepareStatement(sql);

            statement.setString(1, String.valueOf(helpinfo.getUserAFHId()));
            statement.setString(2, String.valueOf(helpinfo.getUserPHId()));
            statement.setString(3, helpinfo.getLocation());
            statement.setString(4, helpinfo.getTime());
            statement.setString(5, helpinfo.getHelpWhat());

            //执行SQL，返回影响记录的行数
            int num = statement.executeUpdate();
            System.out.println("插入成功！" + "受影响的行数为：" + num);
        } catch (SQLException e) {
            System.out.println("err=" + e);
        }
        statement.close();
        connection.close();
    }

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
            while (resultSet.next()) {
                Helpinfo info = new Helpinfo();
                info.setUserAFHId(resultSet.getObject("reqInfo") == null ? "" : resultSet.getString("reqInfo"));
                info.setLocation(resultSet.getObject("location") == null ? "" : resultSet.getString("location"));//要判空！！！
                info.setTime(resultSet.getObject("time") == null ? "" : resultSet.getString("time"));//要判空！！！
                info.setUserPHId(resultSet.getObject("username") == null ? "" : resultSet.getString("username"));//要判空！！！
                info.setHelpWhat(resultSet.getObject("username") == null ? "" : resultSet.getString("username"));//要判空！！！
                helpinfos.add(info);
            }
        } catch (SQLException e) {
            System.out.println("err=" + e);
        } catch (NullPointerException e) {
            System.out.println("err=" + e);
        }
        statement.close();
        connection.close();
        return helpinfos;
    }

    public List<ShowHistory> SearchHistory(int userId) throws SQLException {
        System.out.println("Run at SearchHistory");
        List<ShowHistory> showHistories = null;
        showHistories = new ArrayList<ShowHistory>();//不可少
        ResultSet resultSet = null;
        //   SELECT* FROM学生表INNER JOIN选课表ON学生表.学号=选课表.学号
        String sql = "SELECT   * FROM `help`.`helpinfo` WHERE `userAFHId` like ? ";
        try {
            connection = getConnection();
            //建立传输SQL语句的statement
            statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(userId));
            //执行SQL，返回影响记录的行数
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //               System.out.println(resultSet.getObject("userAFHId"));
                ShowHistory history = new ShowHistory();
                history.setHelpWhat(resultSet.getObject("helpWhat") == null ? "" : resultSet.getString("helpWhat"));
                history.setLocation(resultSet.getObject("location") == null ? "" : resultSet.getString("location"));//要判空！！！
                history.setTime(resultSet.getObject("time") == null ? "" : resultSet.getString("time"));//要判空！！！
                history.setUserAFHId(resultSet.getObject("userAFHId") == null ? "" : resultSet.getString("userAFHId"));//要判空！！！
                history.setUserPHId(resultSet.getObject("userPHId") == null ? "" : resultSet.getString("userPHId"));//要判空！！！

                UserDao userDao = new UserDao();
                User user = new User();

                userDao.SearchUser(Integer.parseInt(history.getUserAFHId()));

                history.setUnmAFH(userDao.SearchUser(Integer.parseInt(history.getUserAFHId())).getUsername());
                history.setPicnumAFH(userDao.SearchUser(Integer.parseInt(history.getUserAFHId())).getPicnum());
                User user1 = userDao.SearchUser(Integer.parseInt(history.getUserPHId()));
                history.setPicnumPH(user1.getPicnum());
                history.setUnmPH(user1.getUsername());
                showHistories.add(history);
            }
        } catch (SQLException e) {
            System.out.println("err=" + e);
        } catch (NullPointerException e) {
            System.out.println("err=" + e);
        }
        statement.close();
        connection.close();
        return showHistories;
    }
}
