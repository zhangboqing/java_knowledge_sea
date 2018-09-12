/**
 * @author zhangboqing
 * @date 2018/9/6
 *
 */
package com.zbq.simpledemo.demo4;


/**
 * 开始netty demo
 * 世界上最简单的协议不是“hello,World!”，而是DISCARD(丢弃)。它是一种协议，在没有任何响应的情况下丢弃任何接收到的数据。
 *
 * 实现一个Discard(抛弃) Server，忽略所有接收到的数据
 * 1.从处理程序DiscardServerHandler实现开始，它处理Netty生成的I/O事件 {@link com.zbq.simpledemo.demo4.DiscardServerHandler}
 *   1）刚开始我们只是简单的extend ChannelInboundHandler类；而不是 implement ChannelHandler接口，自己实现所有的方法。
 *   2）我们override channelRead()方法，该方法属于事件处理方法，当有从客户端client来的新数据被接收时，该方法则被回调，在我们的例子中，我们接收的消息类型是ByteBuf类型。
 *   3）为了实现DISCARD协议，我们DiscardServerHandler必须忽略所接收的消息；ByteBuf是一个reference-counted object (引用统计对象)，我们必须调用release()方法，显示的释放；
 *      我们需要记住,释放传入到Handler中的reference-counted object，是Handler的责任
 *   4）channelRead(),通常实现为下面的形式
 *      @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            try {
            // Do something with msg
            } finally {
            ReferenceCountUtil.release(msg);
        }
}
 * 2.
 */