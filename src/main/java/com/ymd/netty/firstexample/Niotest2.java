package com.ymd.netty.firstexample;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Niotest2 {
    public static void main(String[] args) throws IOException {

        int[] ports = new int[5];
        int port = 6000;
        for (int i = 0; i <= 4; i++) {
            ports[i] = port++;
        }

        Selector selector = Selector.open();
        for (int i = 0; i < ports.length; i++) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            ServerSocket socket = serverSocketChannel.socket();

            InetSocketAddress inetSocketAddress = new InetSocketAddress(ports[i]);
            socket.bind(inetSocketAddress);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听窗口" + ports[i]);
        }

        while (true) {
            int number = selector.select();
            System.out.println("number：" + number);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ);
                    iterator.remove();
                    System.out.println("客户端连接：" + socketChannel);
                } else if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    int byteRead = 0;
                    while (true) {
                        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                        int read = socketChannel.read(buffer);
                        if (read <= 0) {
                            break;
                        }
                        buffer.flip();
                        socketChannel.write(buffer);
                        byteRead += read;
                    }
                    System.out.println("读取："+ byteRead + "来自于：" + socketChannel);
                    iterator.remove();
                }
            }
        }
    }
}
