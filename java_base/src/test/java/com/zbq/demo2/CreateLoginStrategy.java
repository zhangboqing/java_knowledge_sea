package com.zbq.demo2;

import java.util.Random;

/**
 * @author zhangboqing
 * @date 2018/6/17
 */
public class CreateLoginStrategy {

    public static LoginStrategy getLoginStrategy() {

        if (new Random(10).nextInt() > 5) {
            return LoginStrategyEnum.ScanCodeLoginStrategy.getLoginStrategy();
        } else {
            return LoginStrategyEnum.PasswordLoginStrategy.getLoginStrategy();
        }
    }
}
