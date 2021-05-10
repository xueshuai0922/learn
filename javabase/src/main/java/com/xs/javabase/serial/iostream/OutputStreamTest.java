package com.xs.javabase.serial.iostream;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author xueshuai
 * @date 2021/4/26 10:33
 * @description  字节数组
 */
public class OutputStreamTest {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream = new FileOutputStream("test.txt");
            fileOutputStream.write("123".getBytes("utf-8"));
        }catch (Exception e){

        }finally {
            if(fileOutputStream!=null)
            fileOutputStream.close();
        }


        //try括号中写，这里表示当流不使用的时候，jvm会自己关闭这个流
        try( FileOutputStream outputStream= new FileOutputStream("test1.txt")){
                outputStream.write("hello".getBytes("utf-8"));
                outputStream.write("\nWord".getBytes("utf-8"));
        }
    }
}
