package com.help.controller;

import com.help.dao.HelpinfoDao;
import com.help.pojo.Afhinfo;
import com.help.pojo.Helpinfo;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class HistoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            System.out.println("客户端连接HistoryServlet成功");
            HelpinfoDao helpinfoDao = new HelpinfoDao();
            String stringHelpinfo = req.getParameter("addRecord");
            JSONObject jsonHelpinfo = JSONObject.fromObject(stringHelpinfo);
            System.out.println("收到的字符 = " + jsonHelpinfo);
            Helpinfo helpinfo = (Helpinfo) JSONObject.toBean(jsonHelpinfo, Helpinfo.class);
            helpinfoDao.addHelpinfo(helpinfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
