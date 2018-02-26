package com.zbq.controller.spring.retry;

import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * @author zhangboqing
 * @date 2017/12/4
 *
 * 注解方式
 */
@Service("myRetryTestByAnnotation")
public class MyRetryTestByAnnotation {


    @Retryable(Exception.class)
    public void service() throws Exception {
        // ... do something
        System.out.println("执行了");
        throw new Exception("");
    }


    @Retryable(value = {RemoteAccessException.class, RuntimeException.class},
            maxAttempts = 2,
            backoff = @Backoff(value = 2000))
    public void service2() {
        System.out.println("do some things");
        // this exception will just trigger recover1, do not trigger recover3
        throw new RemoteAccessException("remote access exception");
        // this exception will just trigger recover2
//        throw new RuntimeException("runtime exception");

//        System.out.println("do another things");
    }

    // 如果使用注解的话,这个recover只能写在本类中
    @Recover
    public void recover(Exception e) {
        // ... panic
        System.out.println("失败了");
    }

}
