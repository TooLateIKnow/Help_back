package com.help.controller;

import com.help.dao.AfhinfoDao;
import com.help.pojo.Afhinfo;
import com.help.pojo.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class RequestHallServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            System.out.println("客户端连接RequestHallServlet成功");
            JSONArray jsonArray = new JSONArray();
            AfhinfoDao afhinfoDao = new AfhinfoDao();

            List<Afhinfo> lrs =  afhinfoDao.Search();
            for (int i = 0;i<lrs.size();i++){
                jsonArray.add(lrs.get(i));
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
