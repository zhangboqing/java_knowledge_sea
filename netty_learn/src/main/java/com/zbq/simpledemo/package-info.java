/**
 * @author zhangboqing
 * @date 2018/8/29
 *
 * 关于netty ，首先要搞清楚，这是建立在客户端和服务端之间的。
 * 服务端建立相应的规则，然后运行起来，等待客户端访问或者发送”消息“
 *
 * 服务端的建立
 * 第一步：先建立相应的规则 ==> DiscardServerHandler
 * 第二步：我们需要应用相应的规则。就是说，我们建立了接收消息的规则，但是光建立规则有什么用，仅仅只是一个规则，我们需要把这个规则”应用“起来，通常就是我们通常的”运行“;==>DiscardServer
 * 第三步：我们现在相应的规则已经建立，并且”运行“规则的代码也OK，所以运行DiscardServer中   public static void main(String[] args) 启动服务端。
 *
 * 客户端访问
 * 打开命令行窗口，键入  telnet 127.0.0.1 8080 回车，进入telnet 终端
 * 然后随便输入回车，服务端就会打印你输入的数据
 */
package com.zbq.simpledemo;

/**
 *
 *
 * netty 官方API： http://netty.io/4.1/api/index.html
 * netty 中文指南：https://waylau.com/netty-4-user-guide/
 *
 *
 * 关于NIO基础的知识：https://my.oschina.net/andylucc/blog/614295
 *
 *　 　　http://www.cnblogs.com/dolphin0520/p/3919162.html　
 *
 *　　　 http://blog.csdn.net/wuxianglong/article/details/6604817
 *
 */


/**
 * netty 特点：
 * 1.并发高
 *   Netty是一款基于NIO（Nonblocking I/O，非阻塞IO）开发的网络通信框架，对比于BIO（Blocking I/O，阻塞IO），他的并发性能得到了很大提高
 * 2.传输快
 *   Netty使用了NIO中的一大特性——零拷贝,利用直接内存对数据进行操作，从而加快了传输速度
 * 3.封装好
 *
 *
 * Channel
 * 数据传输流，与channel相关的概念有四个：
 *
 * Channel，表示一个连接，可以理解为每一个请求，就是一个Channel。
 *      一个客户端与服务器通信的通道
 * ChannelHandler，核心处理业务就在这里，用于处理业务请求。
 *       业务逻辑处理器，分为ChannelInboundHandler 输入数据处理器
 *                      和ChannelOutboundHandler 输出业务处理器
 *       通常情况下，业务逻辑都是存在于ChannelHandler之中
 * ChannelHandlerContext，用于传输业务数据。
 *      通信管道的上下文
 * ChannelPipeline，用于保存处理过程需要用到的ChannelHandler和ChannelHandlerContext。
 *      用于存放ChannelHandler的容器
 *
 * 他们的交互流程是：
 *
 * 1.事件传递给 ChannelPipeline 的第一个 ChannelHandler
 * 2.ChannelHandler 通过关联的 ChannelHandlerContext 传递事件给 ChannelPipeline 中的 下一个
 *
 * Buffer
 * 他有三种使用模式：
 * 1.Heap Buffer 堆缓冲区
 *   堆缓冲区是ByteBuf最常用的模式，他将数据存储在堆空间。
 * 2.Direct Buffer 直接缓冲区
 *   直接缓冲区是ByteBuf的另外一种常用模式，他的内存分配都不发生在堆，jdk1.4引入的nio的ByteBuffer类允许jvm通过本地方法调用分配内存，这样做有两个好处
 *   通过免去中间交换的内存拷贝, 提升IO处理速度; 直接缓冲区的内容可以驻留在垃圾回收扫描的堆区以外。
 *   DirectBuffer 在 -XX:MaxDirectMemorySize=xxM大小限制下, 使用 Heap 之外的内存, GC对此”无能为力”,也就意味着规避了在高负载下频繁的GC过程对应用线程的中断影响.
 * 3.Composite Buffer 复合缓冲区
 *    复合缓冲区相当于多个不同ByteBuf的视图，这是netty提供的，jdk不提供这样的功能。
 *
 *
 * Codec
 * Netty中的编码/解码器，通过他你能完成字节与pojo、pojo与pojo的相互转换，从而达到自定义协议的目的。
 * 在Netty里面最有名的就是HttpRequestDecoder和HttpResponseEncoder了
 */