package com.xs.javabase.serial;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author xueshuai
 * @date 2021/4/26 13:48
 * @description
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            socket = new Socket("localhost", 8080);
            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            User user = new User();
            user.setAge(1);
            user.setName("xs");
            objectOutputStream.writeObject(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            outputStream.close();
            objectOutputStream.close();
        }

    }
}
