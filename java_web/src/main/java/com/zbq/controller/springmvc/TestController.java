package com.zbq.controller.springmvc;

import com.zbq.controller.springmvc.domain.TestBean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangboqing
 * @date 2017/11/21
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @RequestMapping(value = "/run1" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object getRequestParam(HttpServletRequest request, TestBean testBean) throws Exception {


        String name = testBean.getName();
        System.out.println(name);
        System.out.println(testBean.toString());

        return "success";
    }

}
