package com.shengsiyuan.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

public class NioTest1 {
    public static void main(String[] args) {
        //分配一个大小为10的缓冲区，只能放整数
        IntBuffer buffer = IntBuffer.allocate(10);

        for(int i=0; i<5; i++){
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }

        System.out.println("before flip limit" + buffer.limit());

        buffer.flip();//翻转

        System.out.println("after flip limit" + buffer.limit());
        System.out.println("enter while loop");

        while(buffer.hasRemaining()){
            System.out.println("position: " + buffer.position());
            System.out.println("limit: " + buffer.limit());
            System.out.println("capacity: " + buffer.capacity());

            System.out.println(buffer.get());
        }
    }
}
