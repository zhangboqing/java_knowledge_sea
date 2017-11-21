package com.zbq.jvm.gc;

/**
 * Created by zhangboqing on 2017/7/21.
 * 空间分配担保
 * <p>
 * 在发生Minor GC之前，虚拟机会先检查老年代最大可用的连续空间是否大于新生代所有对象总空间，
 * 如果这个条件成立，那么Minor GC可以确保是安全的。
 * 如果不成立，则虚拟机会查看Han-dlePromotionFailure设置值是否允许担保失败。
 * 如果允许，那么会继续检查老年代最大可用的连续空间是否大于历次晋升到老年代对象的平均大小，
 * 如果大于，将尝试着进行一次Minor GC，尽管这次Minor GC是有风险的；
 * 如果小于，或者Han-dlePromotionFailure设置不允许冒险，那这时也要改为进行一次Full GC。
 *
 * JDK 6 Update 24之后的规则变为只要老年代的连续空间大于新生代对象总大小或者历次晋升的平均大小就会进行Minor GC，否则将进行Full GC。
 */
public class TestHandlePromotion {


    private static final int _1MB = 1024 * 1024;

    /*** VM参数：-Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:-HandlePromotionFailure */
    @SuppressWarnings("unused")
    public static void testHandlePromotion() {

        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;

        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation1 = null;
        allocation4 = new byte[2 * _1MB];
        allocation5 = new byte[2 * _1MB];
        allocation6 = new byte[2 * _1MB];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null;
        allocation7 = new byte[2 * _1MB];
    }

    public static void main(String[] args) {
        testHandlePromotion();
    }
}
