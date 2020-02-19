package com.shengsiyuan.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class NioTest11 {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(address);

        int messageLength = 2 + 3 + 4;

        ByteBuffer[] buffers = new ByteBuffer[3];

        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        while(true){
            int byteRead = 0;

            while(byteRead < messageLength){
                long read = socketChannel.read(buffers);
                byteRead += read;

                System.out.println("bytesRead: " + byteRead);
                Arrays.asList(buffers).stream().map(buffer ->  "position:" + buffer.position() + ", limit: " + buffer.limit())
                        .forEach(System.out::println);
            }

            Arrays.asList(buffers).forEach(buffer -> buffer.flip());

            long bytesWritten = 0;
            while(bytesWritten < messageLength){
                long write = socketChannel.write(buffers);
                bytesWritten += write;
            }

            Arrays.asList(buffers).forEach(buffer -> buffer.clear());

            System.out.println("bytesRead: " + byteRead + ", bytesWritten: " + bytesWritten + ", messageLength: " + messageLength);
        }

    }
}
