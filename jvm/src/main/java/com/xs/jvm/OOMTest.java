package com.xs.jvm;

import java.util.ArrayList;

/**
 * @author xueshuai
 * @date 2021/2/8 21:04
 * @description OOM 测试
 * -Xms1m -Xmx8m -XX:+DumpOnOutOfMemoryError
 */
public class OOMTest {
    private static Byte[] s = new Byte[1024 * 1024];

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        while (true) {
            list.add(s);
        }
    }
}
