package com.xs.netty.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xueshuai
 * @date 2021/2/13 16:28
 * @description
 */
public class WriteBufferTest {
    public static void main(String[] args) throws Exception {

        //输出
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\git\\learn\\netty\\src\\main\\java\\com\\xs\\netty\\nio\\2.txt");
        FileChannel outChannel = fileOutputStream.getChannel();


        ByteBuffer outBuffer = ByteBuffer.allocate(1024);
        String out = "hello world";
        outBuffer.put(out.getBytes());
        outChannel.write(outBuffer);
        fileOutputStream.close();

    }

}
