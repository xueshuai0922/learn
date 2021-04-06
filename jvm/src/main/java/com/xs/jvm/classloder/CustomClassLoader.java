package com.xs.jvm.classloder;

import com.xs.jvm.Car;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author xueshuai
 * @date 2021/3/22 11:02
 * @description 自定义classloader
 */
public class CustomClassLoader extends  ClassLoader{


    //重写findClass方法进行查找class
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        File f = new File("D:\\Car1.class");
        try {
            FileInputStream fis = new FileInputStream(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b = 0;

            while ((b=fis.read()) !=0) {
                baos.write(b);
            }

            byte[] bytes = baos.toByteArray();
            baos.close();
            fis.close();//可以写的更加严谨
            System.out.println("自定义加载");
            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("常规加载");
        return super.findClass(name);
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        CustomClassLoader loader = new CustomClassLoader();
        Class<?> clazz = loader.loadClass("com.xs.jvm.Car1");
        Car car = (Car)clazz.newInstance();
        car.m();

    }
}
