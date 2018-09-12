package com.zbq.springiocv2.spring.context;

/**
 * @author zhangboqing
 * @date 2018/7/14
 */
public abstract class AbstractApplicationContext {

    //提供子类重写
    protected void onRefresh() {
        // For subclasses: do nothing by default.
    }

    protected abstract void refreshBeanFactory();
}
