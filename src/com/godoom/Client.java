package com.godoom;

import java.io.*;
import java.net.Socket;

/**
 * 客户端
 */
public class Client {
    public static void main(String[] args) {
        //1.创建客户端Socket，指定服务器地址和端口
        try {
            Socket socket = new Socket("localhost", 8888);
            //2.获取输出流，向服务器端发出信息
            OutputStream os = socket.getOutputStream();//字节输出流
            PrintWriter pw = new PrintWriter(os);
            pw.write("用户名： tom；密码： 456");
            pw.flush();
            socket.shutdownOutput();//关闭输出流

            //实际情况一般发送的不是字符串是对象
            /*OutputStream osm = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(osm);
            User user = new User("admin", "123");
            oos.writeObject(user);//序列化
            socket.shutdownOutput();*/


            //3.获取输入流，并读取服务器端的相应
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String info = null;
            while ((info = br.readLine()) != null) {
                System.out.println("我是客户端，服务器说：" + info);
            }

            //实际情况也可能接收文件
            /*ObjectInputStream clientInputStream = null;
            //下载后首先生成一个临时文件
            String fileNameTemp = "temp.txt";
            //ConfigManager读取属性文件的工具类
            String downloadPath = ConfigManager.getInstance().getString(Constans.CLIENT_DOWNLOAD_PA);
            try {
                clientInputStream = new ObjectInputStream(socket.getInputStream());
                File fileTemp = new File(downloadPath + "/" + fileNameTemp);
                if (fileTemp.exists()) {
                    fileTemp.delete();
                }
                fileTemp.createNewFile();
                BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(fileTemp));
                //接收服务器的文件
                byte[] buf = new byte[1024];
                int len;
                while ((len = clientInputStream.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                    fos.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }*/

            //4.关闭资源  对于同一个socket，如果关闭了输出流,则与该输出流关联的socket也会被关闭，所以一般不用关闭输出流，直接关闭socket
            br.close();
            isr.close();
            is.close();
            pw.close();
            os.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
