package com.zbq.controller.spring.retry;

import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * @author zhangboqing
 * @date 2017/12/4
 */
public class MyRetryTest {


    public static void main(String [] args) {
        final String params = "传入参数,可为任意类型，final修饰即可";
        // 重试机制
        RetryTemplate oRetryTemplate = new RetryTemplate();
        SimpleRetryPolicy oRetryPolicy = new SimpleRetryPolicy();
        oRetryPolicy.setMaxAttempts(5);// 重试5次
        oRetryTemplate.setRetryPolicy(oRetryPolicy);
        try {
            // obj为doWithRetry的返回结果，可以为任意类型
            Object obj = oRetryTemplate.execute(new RetryCallback<Object, Exception>() {
                @Override
                public Object doWithRetry(RetryContext context) throws Exception {// 开始重试
                    System.out.println(params);
                    testRedo();
                    return "此处可返回操作结果";
                }
            }, new RecoveryCallback<Object>() {
                @Override
                public Object recover(RetryContext context) throws Exception { // 重试多次后都失败了
                    return "失败了";
                }
            });

            System.out.println(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testRedo() {
        System.out.println("执行Redo代码");
        throw new RuntimeException();
    }


}
