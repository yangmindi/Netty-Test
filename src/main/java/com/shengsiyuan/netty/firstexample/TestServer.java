package com.shengsiyuan.netty.firstexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {
    public static void main(String[] args) throws InterruptedException {
        //定义两个线程组(时间循环组)
        /*
        NioEventLoopGroup 可以理解为一个死循环，不断接受客户端的链接
        boss端不断地从客户端接受连接，但是不对链接做任何的处理，接受时候就转给worker
        由worker来完成对链接后续的处理
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            /*
            服务端启动：用于x启动服务端的类
             */
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //group() 第一个参数接受请求，把接受的请求交给第二个参数进行处理

            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)//用到了一个管道NioServerSocketChannel
                    .childHandler(new TestServerInitializer());//可以是自己编写的处理器，请求到达之后，由自己编写的处理器进行处理

            //绑定监听哪个端口
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();//进行同步
            //对关闭的监听
            channelFuture.channel().closeFuture().sync();

        } finally {
            //优雅关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

        }
    }
}
