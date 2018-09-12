package com.zbq.simpledemo.demo4;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author zhangboqing
 * @date 2018/9/6
 *
 * 处理服务器端通道
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {    // (1)


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {    // (2)
        //静静地丢弃接收到的数据

        ((ByteBuf)msg).release();   // (3)
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception { // (4)
        //在引发异常时关闭连接

        cause.printStackTrace();
        ctx.close();
    }
}
