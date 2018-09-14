package com.zbq.base.nio;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhangboqing
 * @date 2018/9/14
 */
public class IOAndNIOTest {


    @Test
    public void iOTest() {

        try (InputStream in = new BufferedInputStream(new FileInputStream("src/nomal_io.txt"))) {
            byte[] buf = new byte[1024];
            int bytesRead = in.read(buf);
            while (bytesRead != -1) {
                new String(buf);
                for (int i = 0; i < bytesRead; i++) {
                    System.out.print((char) buf[i]);
                }
                bytesRead = in.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
