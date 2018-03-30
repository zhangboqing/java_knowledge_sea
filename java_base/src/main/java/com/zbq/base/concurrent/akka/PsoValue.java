package com.zbq.base.concurrent.akka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zhangboqing
 * @date 2018/3/18
 */
public class PsoValue {

    final double value;
    final List<Double> x;

    public PsoValue(double v, List<Double> x) {
        value = v;
        List<Double> b = new ArrayList<Double>(5);
        b.addAll(x);
        this.x = Collections.unmodifiableList(b);

    }

    public double getValue() {
        return value;

    }

    public List<Double> getX() {
        return x;

    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("value:").append(value).append("\n").append(x.toString());
        return sb.toString();

    }
}
