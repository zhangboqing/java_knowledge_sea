package com.zbq.base.concurrent.akka;

/**
 * @author zhangboqing
 * @date 2018/3/18
 */
public class GBestMsg {

    final PsoValue value;

    public GBestMsg(PsoValue v) {
        value = v;

    }

    public PsoValue getValue() {
        return value;

    }
}


