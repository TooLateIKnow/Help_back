package com.help.controller;

import com.help.dao.UserDao;
import com.help.pojo.User;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get user info
        System.out.println("客户端连接RegisterServlet成功");
        String stringUser = req.getParameter("user");
        JSONObject jsonUser = JSONObject.fromObject(stringUser);
        User user = (User)JSONObject.toBean(jsonUser,User.class);
        UserDao adduser = new UserDao();

        int whetherExist = 0;
        try {
            whetherExist = adduser.Search(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PrintWriter out=resp.getWriter();
        String send = null;
        if (whetherExist == 1){
            send = "用户已存在，注册失败";
        }
        else {
            try {
                adduser.AddUser(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            send = "注册成功";
        }
        out.print(send);
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
