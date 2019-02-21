package com.godoom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 使用URL读取页面内容
 */
public class Test03 {

    public static void main(String[] args) {
        //创建一个URL实例
        try {
            URL url = new URL("http://www.baidu.com");
            //通过URL的openStream()获取URL对象所表示的资源的字节输入流
            InputStream is = url.openStream();
            //将字节输入流转换为字符输入流
            InputStreamReader streamReader = new InputStreamReader(is, "utf-8");
            //为字符输入流添加缓冲
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String line = bufferedReader.readLine();//读取数据
            while (line != null) {//循环读取
                System.out.println(line);//输出数据
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            streamReader.close();
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
