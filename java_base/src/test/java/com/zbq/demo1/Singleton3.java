package com.zbq.demo1;

import java.util.Objects;

/**
 * @author zhangboqing
 * @date 2018/6/4
 */
public class Singleton3 {

    private Singleton3() {
    }

    private static Singleton3 instance = null;

    public static Singleton3 getInstance() {

        if (Objects.isNull(instance)) {
            synchronized (Singleton3.class) {

                if (Objects.isNull(instance)) {
                    instance = new Singleton3();
                }
            }
        }

        return instance;
    }

}
