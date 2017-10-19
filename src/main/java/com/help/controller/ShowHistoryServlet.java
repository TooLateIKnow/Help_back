package com.help.controller;

import com.help.dao.AfhinfoDao;
import com.help.dao.HelpinfoDao;
import com.help.pojo.Afhinfo;
import com.help.pojo.ShowHistory;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class ShowHistoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
        System.out.println("客户端连接ShowHistoryServlet成功");
        String userId = req.getParameter("getRecord");

        int id = Integer.parseInt(userId);
        JSONArray jsonArray = new JSONArray();
        HelpinfoDao helpinfoDao = new HelpinfoDao();

        List<ShowHistory> lrs =  helpinfoDao.SearchHistory(id);
        for (int i = 0;i<lrs.size();i++){
            jsonArray.add(lrs.get(i));
            System.out.println(jsonArray.get(i));
        }
        String send = jsonArray.toString();
        PrintWriter out = resp.getWriter();
        out.write(send);
            out.flush();
            out.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
