package com.godoom;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * URL常用方法
 */
public class Test02 {

    public static void main(String[] args) {
        try {
            URL imooc = new URL("http://www.imooc.com/");
            //?后面表示参数，#后面表示锚点
            URL url = new URL(imooc, "/index.html?username=tom#test");
            System.out.println("协议：" + url.getProtocol());
            System.out.println("主体：" + url.getHost());
            //如果未指定端口号，则使用默认端口号（例如http默认80），此时getPort()返回的是-1
            System.out.println("端口：" + url.getPort());
            System.out.println("文件路径：" + url.getPath());
            System.out.println("文件名：" + url.getFile());
            System.out.println("相对路径：" + url.getRef());
            System.out.println("查询字符串：" + url.getQuery());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
