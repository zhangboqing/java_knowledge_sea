package com.zbq.demo2;

/**
 * @author zhangboqing
 * @date 2018/6/17
 */
public enum LoginStrategyEnum {
    PasswordLoginStrategy(new PasswordLoginStrategy()),
    ScanCodeLoginStrategy(new ScanCodeLoginStrategy());

    private LoginStrategy loginStrategy;
    LoginStrategyEnum(LoginStrategy loginStrategy) {
        this.loginStrategy = loginStrategy;
    }

    public LoginStrategy getLoginStrategy() {
        return loginStrategy;
    }

    public void setLoginStrategy(LoginStrategy loginStrategy) {
        this.loginStrategy = loginStrategy;
    }
}
