package com.zbq.jvm.gc;

/**
 * Created by zhangboqing on 2017/7/21.
 * 对象优先在Eden区分配
 *
 * 虚拟机提供了-XX:+PrintGCDetails这个收集器日志参数，告诉虚拟机在发生垃圾收集行为时打印内存回收日志，并且在进程退出的时候输出当前的内存各区域分配情况。
 * (在实际应用中，内存回收日志一般是打印到文件后通过日志工具进行分析，不过本实验的日志并不多，直接阅读就能看得很清楚)
 * <p>
 * Java应用启动时，可以通过设置verbose参数来输出JVM的gc情况，命令如下：java -verbose:gc
 * -Xmx:设置JVM最大可用内存
 * -Xms:设置JVM最小可用内存
 * -Xmn:设置年轻代大小
 * -XX:SurvivorRatio=8:Eden区与一个Survivor区的空间比例 8:1
 * -XX:NewRatio=4:设置年轻代（包括Eden和两个Survivor区）与年老代的比值（除去持久代）,年轻代与年老代所占比值为1：4
 */
public class TestAllocation {

    private static final int _1MB = 1024 * 1024;

    /*** VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8*/
    public static void testAllocation() {

        byte[] allocation1, allocation2, allocation3, allocation4;

        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[5 * _1MB]; // 出现一次Minor GC
    }

    public static void main(String[] args) {
        testAllocation();
    }
}
