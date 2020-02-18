package com.shengsiyuan.nio;

import java.nio.ByteBuffer;

//Slice Buffer与原有Buffer共享相同的底层数组
public class NioTest6 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        buffer.position(2);
        buffer.limit(6);

        ByteBuffer slice = buffer.slice();

        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);
            b *= 2;
            slice.put(i, b);
        }

        buffer.clear();

        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.get());
        }
    }
}
