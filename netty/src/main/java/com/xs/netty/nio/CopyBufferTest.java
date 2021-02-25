package com.xs.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xueshuai
 * @date 2021/2/13 17:36
 * @description
 */
public class CopyBufferTest {

    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("D:\\git\\learn\\netty\\src\\main\\java\\com\\xs\\netty\\nio\\1.txt");
        FileChannel inChannel = inputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\git\\learn\\netty\\src\\main\\java\\com\\xs\\netty\\nio\\1copy.txt");
        FileChannel outChannel = fileOutputStream.getChannel();

        //1.通过buffer进行复制
        ByteBuffer buffer = ByteBuffer.allocate(2014);
        while (true) {
            //循环前重置，因为循环末位有write会把position放后面，需要重新开始
            buffer.clear();
            int read = inChannel.read(buffer);
            System.out.println(read);
            if (read == -1) {
                break;
            }
            buffer.flip();
            outChannel.write(buffer);
        }

        //2.通过通道进行复制
//        outChannel.transferFrom(inChannel,0,16);


        fileOutputStream.close();
        inputStream.close();


    }
}
