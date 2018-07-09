package com.zbq.spring;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

/**
 * @author zhangboqing
 * @date 2018/7/3
 */
public class UseMethodTest {

    @Test
    public void run() {

        TestBean testBean = BeanUtils.instantiateClass(TestBean.class);
        testBean.setAge(0);
        testBean.setName("1");

        System.out.println(testBean);

    }
}
