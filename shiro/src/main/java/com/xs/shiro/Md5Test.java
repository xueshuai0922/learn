package com.xs.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author xueshuai
 * @date 2021/1/10 15:06
 * @description
 */
public class Md5Test {

    public static void main(String[] args) {
        //加盐  并进行hash、
        Md5Hash md5Hash = new Md5Hash("123", "123", 1024);// 9c3b5c0672cd599ccf1019bddaa8089b
        System.out.println(md5Hash.toHex());

    }
}
