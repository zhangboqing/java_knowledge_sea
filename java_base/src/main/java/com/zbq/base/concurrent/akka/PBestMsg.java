package com.zbq.base.concurrent.akka;

public final class PBestMsg {
    final PsoValue value;

    public PBestMsg(PsoValue v) {
        value = v;

    }

    public PsoValue getValue() {
        return value;

    }

    public String toString() {
        return value.toString();

    }
}