package com.zbq.springiocv1.demo.controller;

import com.zbq.springiocv1.demo.service.HelloService;
import com.zbq.springiocv1.spring.annotation.Autowire;
import com.zbq.springiocv1.spring.annotation.Controller;

/**
 * @author zhangboqing
 * @date 2018/7/11
 */
@Controller
public class HelloController {

    @Autowire
    private HelloService helloService;

}
