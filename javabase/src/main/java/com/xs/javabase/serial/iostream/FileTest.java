package com.xs.javabase.serial.iostream;

import java.io.File;
import java.io.IOException;

/**
 * @author xueshuai
 * @date 2021/4/26 9:32
 * @description 文件的获取，绝对路径 相对路径
 */
public class FileTest {
    public static void main(String[] args) throws IOException {

        File file = new File("./pom.xml");
        System.out.println(file);//./pom.xml
        System.out.println(file.getName());//pom.xml
        System.out.println(file.getAbsolutePath());//D:\git\learn\.\pom.xml
        System.out.println(file.isFile());//true


        File context = new File(".");
        System.out.println(context.getAbsolutePath());
        File[] files = context.listFiles();
        for (File subFile : files) {
            if(subFile.isDirectory()){
                System.out.println("目录："+subFile.getName());
            }
            if(subFile.isFile()){

                System.out.println("           文件："+subFile.getName());
            }
        }
    }
}
