package com.zbq.base.io;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author zhangboqing
 * @date 2018/1/12
 */
public class FileIOTest {


    @Test
    public void run1() throws IOException {

        String fileName = "波清测试";
        File file = FileUtils.getFile("/Users/zhangboqing/Downloads/", fileName);

        FileOutputStream fileOutputStream = new FileOutputStream(file);

        fileOutputStream.write("222".getBytes());

    }

    @Test
    public void run2() {

//
//        try {
//            System.out.println("1");
//            return;
//        } finally {
//            System.out.println(2);
//        }

        BigDecimal bigDecimal = new BigDecimal("+"+"1");
        System.out.println(bigDecimal);

    }

    @Test
    public void run3() throws IOException {

        String fileName = "波清测试";
//        File file = FileUtils.getFile("/usr/local/http/cloudfile/", fileName);
        File file = new File("/usr/local/http/cloudfile/" + fileName);

        FileOutputStream fileOutputStream = new FileOutputStream(file);

        fileOutputStream.write("222".getBytes());

    }


}
