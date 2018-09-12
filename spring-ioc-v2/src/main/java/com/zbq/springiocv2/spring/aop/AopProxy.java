package com.zbq.springiocv2.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhangboqing
 * @date 2018/7/15
 */
public class AopProxy implements InvocationHandler {

    private AopConfig config;
    private Object target;


    public Object getProxy(Object instance) {
        this.target = instance;
        Class<?> clazz = instance.getClass();
       return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    public void setConfig(AopConfig config) {
        this.config = config;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Method m = this.target.getClass().getMethod(method.getName(), method.getParameterTypes());

        //在原始方法调用以前要执行增强的代码
        //这里需要通过原生方法去找，通过代理方法去Map中是找不到的
        if (config.contains(m)) {
            AopConfig.Aspect aspect = config.get(method);
            Method[] points = aspect.getPoints();
            points[0].invoke(aspect.getAspect());
        }

        Object object = method.invoke(this.target, args);

        //在原始方法调用以后要执行增强的代码
        if (config.contains(m)) {
            AopConfig.Aspect aspect = config.get(method);
            Method[] points = aspect.getPoints();
            points[1].invoke(aspect.getAspect());
        }


        return object;
    }
}
