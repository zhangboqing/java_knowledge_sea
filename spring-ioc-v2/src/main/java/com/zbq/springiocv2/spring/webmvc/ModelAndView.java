package com.zbq.springiocv2.spring.webmvc;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * @author zhangboqing
 * @date 2018/7/15
 */
@Data
@AllArgsConstructor
public class ModelAndView {
    private String viewName;
    private Map<String,?> model;

}
