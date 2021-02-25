package com.xs.netty.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xueshuai
 * @date 2021/2/13 16:28
 * @description
 */
public class ReadBufferTest {
    public static void main(String[] args) throws Exception {
        //读取
        FileInputStream inputStream = new FileInputStream("D:\\git\\learn\\netty\\src\\main\\java\\com\\xs\\netty\\nio\\1.txt");
        FileChannel inChannel = inputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(19);

        while (true) {
            int read = inChannel.read(byteBuffer);
            if (read == -1) {
                break;
            }
            byteBuffer.flip();
            System.out.println(new String(byteBuffer.array()));
        }
        inputStream.close();


    }

}
