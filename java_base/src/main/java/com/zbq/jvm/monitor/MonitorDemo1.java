package com.zbq.jvm.monitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangboqing on 2017/7/26.
 * 运行时设置的虚拟机参数为：-Xms100m -Xmx100m -XX:+UseSerialGC
 * 这段代码的作用是以64KB/50毫秒的速度往Java堆中填充数据，一共填充1000次，使用JConsole的“内存”页签进行监视，观察曲线和柱状指示图的变化。
 */
public class MonitorDemo1 {


    /**
     * 内存占位符对象，一个OOMObject大约占64KB
     */
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<OOMObject>();
        for (int i = 0; i < num; i++) {
            // 稍作延时，令监视曲线的变化更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws
            Exception {
        fillHeap(1000);
    }
}
