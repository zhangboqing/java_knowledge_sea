package com.zbq.base.concurrent.akka;

import java.util.List;

/**
 * @author zhangboqing
 * @date 2018/3/18
 */
public class Fitness {
    public static double fitness(List<Double> x) {
        double sum = 0;
        for (int i = 1; i < x.size(); i++) {
            sum += Math.sqrt(x.get(i));

        }
        return sum;

    }
}
