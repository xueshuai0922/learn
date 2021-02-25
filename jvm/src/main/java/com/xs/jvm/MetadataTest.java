package com.xs.jvm;

/**
 * @author xueshuai
 * @date 2021/2/8 20:12
 * @description 元空间测试：逻辑上存在，物理上不存在堆中
 */
public class MetadataTest {
    public static void main(String[] args) {
        long l = Runtime.getRuntime().maxMemory();
        System.out.println("最大内存： " + l / (double) 1024 / 1024 + "mb");
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("初始化内存： " + totalMemory / (double) 1024 / 1024 + "mb");
    }
}
