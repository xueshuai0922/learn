package com.xs.netty.bio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xueshuai
 * @date 2021/2/10 17:14
 * @description bio服务端
 */
public class BioSocketTest {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8090);
        System.out.println("1）new serverSocket(8090)....");
        while (true) {
            //接受客户端
            Socket client = serverSocket.accept();//会阻塞
            System.out.println("2）接受到client的端口号：" + client.getPort());


            //创建线程来处理接受到的client（因为main线程在accept处阻塞了，无法进行处理客户端信息了）
            //也可以用线程池来操作
            new Thread(new Runnable() {
                Socket ss;

                public Runnable setSS(Socket s) {
                    ss = s;
                    return this;
                }

                @Override
                public void run() {
                    try {
                        InputStream inputStream = ss.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        while (true) {
                            System.out.println("3)读客户端的信息");
                            System.out.println(bufferedReader.readLine());//这里也是阻塞的，一直在读
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    } finally {
                        System.out.println("4）读取结束");

                    }
                }
            }.setSS(client)).start();


        }


    }
}
