package com.shengsiyuan.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest3 {
    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("NioTest3.txt");

        byte[] message = "hyh ymd".getBytes();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        FileChannel channel = fileOutputStream.getChannel();

        for (int i = 0; i < message.length; i++) {
            byteBuffer.put(message[i]);
        }

        String a = "sdasd";


        byteBuffer.flip();

        channel.write(byteBuffer);

        fileOutputStream.close();
    }
}
