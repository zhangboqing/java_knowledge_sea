package com.zbq.springiocv1.demo.service;

import com.zbq.springiocv1.spring.annotation.Service;

/**
 * @author zhangboqing
 * @date 2018/7/11
 */
@Service("helloService")
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello() {
        System.out.println("Hello World!!!");
    }
}
