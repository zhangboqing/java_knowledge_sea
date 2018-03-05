package com.zbq.jvm.classreload.class_load_order.one;

/**
 * @author zhangboqing
 * @date 2018/2/26
 */
public class NotInitialization
{
    public static void main(String[] args)
    {
        System.out.println(SubClass.value);
    }
}
