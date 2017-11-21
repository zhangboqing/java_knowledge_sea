package com.zbq.jvm.memory;

import org.junit.Test;

/**
 * @author zhangboqing
 * @date 2017/11/16
 */
public class DemoTest {

    @Test
    public void run1() {


        String str1 = new StringBuilder("计算机").append("软件啊").toString();
        String str2 = new StringBuilder("ja").append("va").toString();
//        String str3 = "计算机软件啊";
//        String str4 = "java";

        System.out.println(str1.intern() == str1);
        System.out.println(str2.intern() == str2);
//        System.out.println(str3.intern() == str3);
//        System.out.println(str4.intern() == str4);
    }
 }
