package com.help.controller;

import com.help.dao.AfhinfoDao;
import com.help.pojo.Afhinfo;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class PublishServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        System.out.println("客户端连接PublishServlet成功");
        AfhinfoDao afhinfoDao = new AfhinfoDao();
        String stringPublish = req.getParameter("help");
        JSONObject jsonPublish = JSONObject.fromObject(stringPublish);
        System.out.println("收到的字符 = " + jsonPublish);
        Afhinfo afhinfo = (Afhinfo) JSONObject.toBean(jsonPublish, Afhinfo.class);
        afhinfoDao.addAfhinfo(afhinfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
