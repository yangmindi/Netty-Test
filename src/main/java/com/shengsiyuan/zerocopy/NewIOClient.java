package com.shengsiyuan.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost",8899);
        socketChannel.connect(inetSocketAddress);
        socketChannel.configureBlocking(true);

        String filename = "/Users/didi/zhanglong_Netty/out2.txt";
        FileChannel fileChannel = new FileInputStream(filename).getChannel();

        long startTime = System.currentTimeMillis();
        long sendCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送的总字节数：" + sendCount +"，耗时时间："+ (System.currentTimeMillis() - startTime));
        fileChannel.close();


    }
}
