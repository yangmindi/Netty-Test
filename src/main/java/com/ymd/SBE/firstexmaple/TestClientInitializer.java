package com.ymd.SBE.firstexmaple;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import secondTest.MessageDecoder;
import secondTest.MessageEncoder;


public class TestClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("tagDecoder", new LengthFieldBasedFrameDecoder(4069, 0, 4, 0, 0)); //Size is 4k at most
        pipeline.addLast(new TestClientHandler());
    }
}
