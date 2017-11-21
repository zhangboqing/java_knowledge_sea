package com.zbq.jvm.gc;

/**
 * Created by zhangboqing on 2017/7/21.
 * 将大对象直接分配进入老年代
 *
 * 虚拟机提供了一个-XX:PretenureSizeThresh-old参数，令大于这个设置值的对象直接在老年代分配,这样做的目的是避免在Eden区及两个Sur-vivor区之间发生大量的内存复制
 * (注意Pretenure-SizeThreshold参数只对Serial和ParNew两款收集器有效)
 */
public class TestPretenureSizeThreshold {

    private static final int _1MB = 1024 * 1024;

    /*** VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728 */
    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        allocation = new byte[4 * _1MB]; //直接分配在老年代中
    }

    public static void main(String[] args) {
        testPretenureSizeThreshold();
    }

}
