package com.xs.netty.tcp.fix.delimiter;

/**
 * @author xueshuai
 * @date 2021/2/23 10:18
 * @description
 */
public class CustomProto {
    private long length;
    private byte[] data;

    public CustomProto() {
    }

    public CustomProto(long length, byte[] data) {
        this.length = length;
        this.data = data;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
