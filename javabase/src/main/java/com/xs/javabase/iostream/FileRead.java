package com.xs.javabase.iostream;

import cn.hutool.core.io.file.FileReader;

import java.io.*;
import java.util.List;

/**
 * @author xueshuai
 * @date 2021/4/22 10:31
 * @description 读取文件
 */
public class FileRead {
    public static void main(String[] args) {
//        FileReader fileReader = new FileReader("d://1.txt");
//        List<String> strings = fileReader.readLines();
//        strings.forEach(System.out::println);
        readByInputStream();
    }

    public static void readByInputStream()  {
        InputStream inputStream=null;
        try {
            File file = new File("d://1.txt");
            inputStream = new FileInputStream(file);
            System.out.println(inputStream);
            StringBuilder sb = new StringBuilder();
            int read=0;
            while ((read=inputStream.read())!=-1) {
                    sb.append((char) read);
            }
            System.out.println(sb);


        }catch (Exception e){

        }finally {
            try {
                if(inputStream!=null)
                inputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
