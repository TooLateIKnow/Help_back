package com.help.Chat;


import com.help.pojo.User;
import net.sf.json.JSONObject;

public class TestServerSocket {

    public static void main(String[] args) {

        User user = new User("1","2","3","4","5");
        JSONObject root = JSONObject.fromObject(user);
        System.out.println(root.toString());
        new ServerListener().start();
        System.out.println("�������ѿ���������");
    }
}

