package com.help.Chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;


public class ServerListener extends Thread{

    @Override
    public void run() {
        //�˿ڷ�Χ:1-65535
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(8081);

            while(true) {
                Socket socket = serverSocket.accept();

                System.out.println("已经连接");

                //JOptionPane.showMessageDialog(null, "�пͻ������ӵ���1989�˿�");
                System.out.println("�пͻ������ӵ���8081�˿�");
                ChatSocket cs = new ChatSocket(socket);
                cs.start();
                ChatManager.getChatManager().add(cs);
            }
        } catch (IOException e) {
            System.out.println("���Ӵ���");
        }
    }
}

