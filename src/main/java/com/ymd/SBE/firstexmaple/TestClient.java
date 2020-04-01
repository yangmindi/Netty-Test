package com.ymd.SBE.firstexmaple;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TestClient {
    public static void main(String[] args) throws Exception {
        EventLoopGroup loopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(loopGroup).channel(NioSocketChannel.class).handler(new TestClientInitializer());
            ChannelFuture channel = bootstrap.connect("localhost", 8899).sync();
            channel.channel().closeFuture().sync();

        } finally {
            loopGroup.shutdownGracefully();
        }
    }
}
