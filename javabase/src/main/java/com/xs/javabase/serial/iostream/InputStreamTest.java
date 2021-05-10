package com.xs.javabase.serial.iostream;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author xueshuai
 * @date 2021/4/26 10:18
 * @description
 */
public class InputStreamTest {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream=null;
        try {
             fileInputStream = new FileInputStream("test.txt");
            int temp;
            byte[] bytes = new byte[1024];
            while ((temp =fileInputStream.read(bytes))!=-1){
//                System.out.println(temp);
                //将二进制 强转为char
                System.out.print((char)temp);//编码问题

            }
        }catch (Exception e ){
            e.printStackTrace();
        }finally {
            fileInputStream.close();
        }

    }
}
