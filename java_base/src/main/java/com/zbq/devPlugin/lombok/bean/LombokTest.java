package com.zbq.devPlugin.lombok.bean;

import org.junit.Test;

/**
 * @author zhangboqing
 * @date 2018/6/13
 */
public class LombokTest {

    public static void main(String[] args) {

        User build = User.builder().skuId(11L).build();
        Inter1 inter1 = new Inter1Impl();

        System.out.println(build);
    }

    @Test
    public void  run() {
        User3 user3 = new User3(1L);

        System.out.println(user3.getSkuId());
    }
}
