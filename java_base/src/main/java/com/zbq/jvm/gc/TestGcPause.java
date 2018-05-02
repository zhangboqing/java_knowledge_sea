package com.zbq.jvm.gc;

/**
 * @author zhangboqing
 * @date 2018/5/1
 */
public class TestGcPause {

    private static final int _100MB = 100*1024 * 1024;

    /*** VM参数：-Xms800M -Xmx800M -Xmn500M  -XX:SurvivorRatio=8*/
    public static void main(String[] args) {


        new Thread(()-> {

            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(System.currentTimeMillis()/1000);
            }

        }).start();


        new Thread(()-> {

            while (true) {
                byte[] allocation1, allocation2, allocation3, allocation4;
                allocation1 = new byte[2 * _100MB];
                allocation2 = new byte[2 * _100MB];
                allocation4 = new byte[1 * _100MB]; // 出现一次Minor GC
            }

        }).start();

    }


}
