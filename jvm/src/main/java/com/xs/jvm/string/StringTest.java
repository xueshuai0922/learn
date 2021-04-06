package com.xs.jvm.string;

/**
 * @author xueshuai
 * @date 2021/3/21 21:48
 * @description
 */
public class StringTest {


    public static void main(String[] args) {
        String s = "34";
        String ss="34";
        String s3="34";
        String s4 = s3.intern();

        System.out.println(s == ss);
        System.out.println(ss == s3);
        System.out.println(s3==s4);

    }
}
