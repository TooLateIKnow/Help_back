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

public class ChangeInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        int changeSuccess = 0;
        //手机、邮箱、用户名
        System.out.println("客户端连接ChangeInfoServlet成功");

        ///首先，需要获取客户端的JSON字符串
        String stringUpdata = req.getParameter("user");
        JSONObject jsonUpdata = JSONObject.fromObject(stringUpdata);
        User user = (User) JSONObject.toBean(jsonUpdata, User.class);

        //如果修改了手机号码
        //TODO:updata手机号码
        try {
            if (user.getMobilephone() != null) {
                changeSuccess = userDao.changeMobilephone(user);
            } else if (user.getUsermail() != null) {//如果修改了邮箱
                //TODO:updata邮箱
                changeSuccess = userDao.changeUsermail(user);
            } else if (user.getUsername() != null) {//如果修改了用户名
                //TODO:updata用户名
                changeSuccess = userDao.changeUsername(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {//具体的你自己该，插入成功，就返回“修改成功”
            PrintWriter out = resp.getWriter();
            if(changeSuccess == 1){
                out.write("修改成功");
            }else {
                out.write("修改失败");
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
