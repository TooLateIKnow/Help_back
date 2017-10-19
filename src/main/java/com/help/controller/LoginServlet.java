package com.help.controller;


import com.help.dao.UserDao;
import com.help.pojo.User;
import net.sf.json.JSON;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("客户端连接LoginServlet成功");
        String send =null;
        String stringUser = request.getParameter("user");
        JSONObject jsonUser = JSONObject.fromObject(stringUser);
        User user = (User)JSONObject.toBean(jsonUser,User.class);
        UserDao searchPassword = new UserDao();
        String phoneNum = user.getMobilephone();
        try {
            User searchResult = searchPassword.SearchPassword(phoneNum);

            if(searchResult.getUserId() == 0000){
                send = "0000";
            }
            else{
                JSONObject sendUser = JSONObject.fromObject(searchResult);
                send = sendUser.toString();
                //System.out.println("最终结果 send"+send);
            }
            PrintWriter out=response.getWriter();
            out.write(send);
            out.flush();
            out.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


}
