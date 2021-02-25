package com.xs.netty.nio;

import java.nio.IntBuffer;

/**
 * @author xueshuai
 * @date 2021/2/13 11:38
 * @description
 */
public class BasicBufferTest {

    public static void main(String[] args) {
        //给个
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i + 2);
        }
        //缓存区翻转，position 归0，limit到position的最终大小
        intBuffer.flip();
        //判断如果limit - position>0
        while (intBuffer.remaining() > 0) {
            //get（）自动进行position+1 获取值
            System.out.println(intBuffer.get());
        }
    }
}
