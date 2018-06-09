package com.zbq.demo1;

/**
 * @author zhangboqing
 * @date 2018/6/4
 */
public class Singleton2 {

    private Singleton2() {
    }

    private static final Singleton2 instance = new Singleton2();

    public static Singleton2 getInstance() {
        return instance;
    }

}
