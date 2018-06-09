package com.zbq.demo1;

/**
 * @author zhangboqing
 * @date 2018/6/4
 */
public class Singleton {

    private Singleton() {
    }

    public static Singleton getInstance() {
        return InnerClass.instance;
    }

    public static class InnerClass {
        private static Singleton instance = new Singleton();
    }

}
