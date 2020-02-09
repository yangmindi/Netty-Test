package com.shengsiyuan.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

public class NioTest1 {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);//分配一个大小为10的缓冲区，只能放整数

        for(int i=0; i<buffer.capacity(); i++){
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }

        buffer.flip();//翻转

        while(buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }
}
