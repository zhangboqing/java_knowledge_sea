package com.zbq.springiocv2.spring.beans;

import com.zbq.springiocv2.spring.aop.AopConfig;
import com.zbq.springiocv2.spring.aop.AopProxy;
import com.zbq.springiocv2.spring.core.FactoryBean;

/**
 * @author zhangboqing
 * @date 2018/7/15
 */
public class BeanWrapper extends FactoryBean {

    private AopProxy aopProxy = new AopProxy();

    //还会用到  观察者  模式
    //1、支持事件响应，会有一个监听
    private BeanPostProcessor beanPostProcessor;

    private Object wrapperInstance;
    //原始的通过反射new出来，要把包装起来，存下来
    private Object originalInstance;

    public BeanPostProcessor getBeanPostProcessor() {
        return beanPostProcessor;
    }

    public void setBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessor = beanPostProcessor;
    }

    public BeanWrapper(Object instance) {
        //从这里开始，我们要把动态的代码添加进来了
        this.wrapperInstance = aopProxy.getProxy(instance);
        this.originalInstance = originalInstance;
    }

    public Object getWrapperInstance() {
        return wrapperInstance;
    }

    // 返回代理以后的Class
    // 可能会是这个 $Proxy0
    public Class getWrapperClass() {
        return wrapperInstance.getClass();
    }

    public Object getOriginalInstance() {
        return originalInstance;
    }

    public void setOriginalInstance(Object originalInstance) {
        this.originalInstance = originalInstance;
    }

    public void setAopConfig(AopConfig aopConfig) {
        this.aopProxy.setConfig(aopConfig);
    }
}
