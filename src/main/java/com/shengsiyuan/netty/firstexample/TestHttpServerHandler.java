package com.shengsiyuan.netty.firstexample;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /*
    读取客户端发送的请求 ，并且向客户端返回响应
     */
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        System.out.println(msg.getClass());

        if (msg instanceof HttpRequest) {
//            System.out.println("执行Channel0");

            //如果是Http请求，就做一个类型转换
            HttpRequest httpRequest = (HttpRequest) msg;
            System.out.println("请求方法的名字：" + httpRequest.method().name());

            //如果是请求获取icon图标就不往下执行了
            URI uri = new URI(httpRequest.uri());
            if("/favicon.ico".equals(uri.getPath())){
                System.out.println("请求favicon.ico");
                return;
            }

            ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);//返回给客户端的响应内容

            //FullHttpResponse 响应对象
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            //返回给客户端
            /*
            write()：放到缓冲区
            writeAndFlush()：返回给客户端
             */
            ctx.writeAndFlush(response);
            ctx.channel().closeFuture();
        }
    }

    //通道再活跃状态的时候采取的方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel Active");
        super.channelActive(ctx);
    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel Registered");
        super.channelRegistered(ctx);
    }

    //通道被添加
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Handler Added");
        super.handlerAdded(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel Inactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel Unregistered");
        super.channelUnregistered(ctx);
    }
}
