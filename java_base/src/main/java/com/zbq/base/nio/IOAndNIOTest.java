package com.zbq.base.nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zhangboqing
 * @date 2018/9/14
 */
public class IOAndNIOTest {


    //同步阻塞IO
    @Test
    public void iOTest() {

        try (FileInputStream fileInputStream = new FileInputStream("src/main/java/1.properties");
             InputStream in = new BufferedInputStream(fileInputStream)) {

            byte[] buf = new byte[1024];
            int bytesRead = in.read(buf);
            while (bytesRead != -1) {
                System.out.println(new String(buf));
                for (int i = 0; i < bytesRead; i++) {
                    System.out.print((char) buf[i]);
                }
                bytesRead = in.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //同步非阻塞NIO
    @Test
    public void nIOTest() {

        try (RandomAccessFile aFile = new RandomAccessFile("src/main/java/1.properties", "rw");
             FileChannel fileChannel = aFile.getChannel();) {

            ByteBuffer buf = ByteBuffer.allocate(1024);
            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);
            while (bytesRead != -1) {
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }
                buf.compact();
                bytesRead = fileChannel.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
