package com.shengsiyuan.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioTest12 {
    public static void main(String[] args) throws IOException {
        int[] ports = new int[5];

        ports[0] = 6000;
        ports[1] = 6001;
        ports[2] = 6002;
        ports[3] = 6003;
        ports[4] = 6004;

        Selector selector = Selector.open();
        for (int i = 0; i < ports.length; i++) {
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            socketChannel.configureBlocking(false);
            ServerSocket socket = socketChannel.socket();

            InetSocketAddress address = new InetSocketAddress(ports[i]);
            socket.bind(address);

            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听窗口" + ports[i]);
        }

        while (true) {
            //如果有值说明存在事件，返回key的数量
            int number = selector.select();
            System.out.println("number:" + number);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                //有链接进来
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ);
                    iterator.remove();
                    System.out.println("获取客户端连接：" + socketChannel);
                }else if(selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                    int byteRead = 0;

                    while(true){
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        byteBuffer.clear();

                        int read = socketChannel.read(byteBuffer);
                        if(read <= 0){
                            break;
                        }

                        byteBuffer.flip();

                        socketChannel.write(byteBuffer);
                        byteRead += read;
                    }
                    System.out.println("读取："+ byteRead + "来自于：" + socketChannel);
                    //这个事件处理完之后一定要删除掉
                    iterator.remove();
                }
            }
        }
    }
}
