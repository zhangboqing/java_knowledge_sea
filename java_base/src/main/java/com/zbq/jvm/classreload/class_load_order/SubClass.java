package com.zbq.jvm.classreload.class_load_order;

/**
 * @author zhangboqing
 * @date 2018/2/26
 */
public class SubClass extends SuperClass
{
    static
    {
        System.out.println("SubClass init");
    }

    static int a;

    public SubClass()
    {
        System.out.println("init SubClass");
    }
}
