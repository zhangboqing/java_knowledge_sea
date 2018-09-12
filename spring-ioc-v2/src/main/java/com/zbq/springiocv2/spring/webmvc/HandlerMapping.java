package com.zbq.springiocv2.spring.webmvc;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @author zhangboqing
 * @date 2018/7/15
 */
@Data
@AllArgsConstructor
public class HandlerMapping {
    private Object controller;
    private Method method;
    private Pattern pattern; //url封装

}
