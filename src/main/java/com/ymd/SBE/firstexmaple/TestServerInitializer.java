package com.ymd.SBE.firstexmaple;

import com.shengsiyuan.netty.sixthexample.MyDataInfo;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import secondTest.MessageDecoder;
import secondTest.MessageEncoder;
import secondTest.MessageHeaderDecoder;
import secondTest.MessageHeaderEncoder;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //内置编码解码器，将字节数组和对象之间转换
        System.out.println("testServerInitializer");
        pipeline.addLast("tagDecoder", new LengthFieldBasedFrameDecoder(4069, 0, 4, 0, 0)); //Size is 4k at most
        pipeline.addLast(new TestServerHandler());
    }
}
