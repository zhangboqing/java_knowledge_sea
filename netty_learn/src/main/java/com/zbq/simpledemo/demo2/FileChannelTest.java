package com.zbq.simpledemo.demo2;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zhangboqing
 * @date 2018/8/31
 */
public class FileChannelTest {

    /**
     * 该程序会向工程目录下的data.txt文件写入字符串"java nio"，注意在调用channel的write方法之前必须调用buffer的flip方法，否则无法正确写入内容
     *  1. 从FileInputStream获取Channel
     *  2. 创建Buffer
     *  3. 将数据从Channel写入到Buffer中
     */
    public static void main(String[] args) throws IOException {
        File file = new File("data.txt");
        FileOutputStream outputStream = new FileOutputStream(file);
        FileChannel channel = outputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String string = "java nio";
        buffer.put(string.getBytes());
        buffer.flip();     //此处必须要调用buffer的flip方法
        channel.write(buffer);
        channel.close();
        outputStream.close();
    }


    /** 使用NIO读取数据
     *  1. 从FileInputStream获取Channel
     *  2. 创建Buffer
     *  3. 将数据从Channel读取到Buffer中
     */
    @Test
    public void run() throws Exception {
        File file = new File("data.txt");
        FileInputStream fin = new FileInputStream(file);

        // 获取通道
        FileChannel fc = fin.getChannel();

        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 读取数据到缓冲区
        fc.read(buffer);

        buffer.flip();

        while (buffer.remaining() > 0) {
            byte b = buffer.get();
            System.out.print(((char) b));
        }

        fin.close();
    }

}
