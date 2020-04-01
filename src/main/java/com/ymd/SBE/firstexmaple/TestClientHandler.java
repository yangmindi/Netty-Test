package com.ymd.SBE.firstexmaple;

import com.shengsiyuan.netty.sixthexample.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.agrona.concurrent.UnsafeBuffer;
import secondTest.MessageEncoder;
import secondTest.MessageHeaderEncoder;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Random;

import static java.nio.file.StandardOpenOption.*;

public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        final ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4096);
        final UnsafeBuffer directBuffer = new UnsafeBuffer(byteBuffer);
        MessageHeaderEncoder messageHeaderEncoder = new MessageHeaderEncoder();


        //编码
        MessageEncoder messageEncoder = new MessageEncoder();
        messageEncoder.wrapAndApplyHeader(directBuffer, 0, messageHeaderEncoder)
                .age(20)
                .name(new String("张三".getBytes(MessageEncoder.nameCharacterEncoding()), StandardCharsets.UTF_8))
                .address(new String("北京".getBytes(MessageEncoder.addressCharacterEncoding()), StandardCharsets.UTF_8));

        int length = messageEncoder.encodedLength() + messageHeaderEncoder.encodedLength();
        System.out.println("TestClientHandler");
        //发送给服务器端
        ctx.channel().writeAndFlush(directBuffer);


    }
}