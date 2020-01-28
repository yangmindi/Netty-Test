package com.shengsiyuan.netty.firstexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    //相当于一个初始化器，是一个回调方法
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();//一个pipeline有很多的ChannelHandler，相当于一个拦截器

        pipeline.addLast("httpServerCodec", new HttpServerCodec());//对web的请求和响应进行编解码
        pipeline.addLast("testHttpServerHandler", new TestHttpServerHandler());
    }
}
