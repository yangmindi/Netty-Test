package com.ymd.SBE.firstexmaple;

import com.shengsiyuan.netty.sixthexample.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.agrona.concurrent.UnsafeBuffer;
import secondTest.MessageDecoder;
import secondTest.MessageEncoder;
import secondTest.MessageHeaderDecoder;

import java.nio.ByteBuffer;


public class TestServerHandler extends SimpleChannelInboundHandler<UnsafeBuffer> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UnsafeBuffer directBuffer) throws Exception {
        System.out.println("TestServerHandler");
        MessageHeaderDecoder messageHeaderDecoder = new MessageHeaderDecoder();
        messageHeaderDecoder.wrap(directBuffer, 0);
        int blockLength = messageHeaderDecoder.blockLength();
        int version = messageHeaderDecoder.version();

        final StringBuilder sb = new StringBuilder();
        MessageDecoder messageDecoder = new MessageDecoder();
        messageDecoder.wrap(directBuffer, messageHeaderDecoder.encodedLength(), blockLength, version);
        sb.append("\nname=").append(messageDecoder.name());
        sb.append("\nage=").append(messageDecoder.age());
        sb.append("\naddress=").append(messageDecoder.address());
        System.out.println(sb);
    }
}
