package com.godoom;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基于tcp协议的Socket通信，实现用户登陆
 * 服务器端
 */
public class Server {
    public static void main(String[] args) {
        try {
            //1.创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = null;
            int count = 0;
            //记录客户端的数量
            System.out.println("***服务器即将启动，等待客户端连接***");
            //循环监听等待客户端的连接
            while (true) {
                //调用accept()开始监听，等待客户端的连接
                socket = serverSocket.accept();
                //创建一个新的线程
                ServerThread serverThread = new ServerThread(socket);
                //根据实际情况设置线程的优先级，未设置可能会导致运行时速度非常慢
                serverThread.setPriority(4);//设置线程优先级，[1-10]默认为5
                //启动线程
                serverThread.start();

                count++;//统计客户端的数量
                System.out.println("客户端的数量：" + count);
                InetAddress address = socket.getInetAddress();
                System.out.println("当前客户端的IP：" + address.getHostAddress());
            }
            //serverSocket.close();上面是死循环执行不到serverSocket关闭
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
