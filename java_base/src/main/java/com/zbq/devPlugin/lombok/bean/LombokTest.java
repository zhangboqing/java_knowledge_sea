package com.zbq.devPlugin.lombok.bean;

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
}
