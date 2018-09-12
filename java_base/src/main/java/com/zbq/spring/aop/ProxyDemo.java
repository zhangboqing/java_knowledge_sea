package com.zbq.spring.aop;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhangboqing
 * @date 2018/8/29
 */
public class ProxyDemo {

    public static void main(String[] args) {
        Proxy.newProxyInstance(Object.class.getClassLoader(),
                Object.class.getInterfaces(),
                (Object proxy, Method method, Object[] args2)->{
                    //开启事物 TODO
                    Object invoke = method.invoke(null, null);
                    //提交事物 TODO
                    return invoke;
                }
        );
    }
}
