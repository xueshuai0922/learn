package javabase.serial;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author xueshuai
 * @date 2021/4/26 13:25
 * @description server socket  nio 服务端，用于测试传递一个java对象
 */
public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = null;
        InputStream inputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            serverSocket = null;
            serverSocket = new ServerSocket(8080);

            Socket accept = serverSocket.accept();
            //从socket中获取输入流
            inputStream = accept.getInputStream();
            //包装到objectinput流中
            objectInputStream = new ObjectInputStream(inputStream);
            //从object流中进行读取对象
            User user = (User) objectInputStream.readObject();
            System.out.println(user);
            Thread.sleep(50 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            serverSocket.close();
        }


    }
}
