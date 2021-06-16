package com.xs.javabase.trycacthfinally;

/**
 * @author xueshuai
 * @date 2021/5/12 12:10
 * @description
 */
public class TryCatchFinallyTest {

    private  static int s =9;

    public static void main(String[] args) {

        try {
            s=10;
            System.out.println(s);
            return;
        }catch (Exception e){

        }finally {
            s=12;
            System.out.println(s);
        }
    }
}
