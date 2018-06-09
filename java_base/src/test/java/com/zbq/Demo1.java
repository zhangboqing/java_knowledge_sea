package com.zbq;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhangboqing
 * @date 2018/6/4
 */
public class Demo1 {


    @Test
    public void run() {

        boolean bb = is2NPower2(1);
        System.out.println(bb);

//        System.out.println(IdGenerate.nextID("a"));
//        System.out.println(IdGenerate.nextID("a"));
//        System.out.println(IdGenerate.nextID("a"));
    }


    /*2：
    int a 判断a是否是2的n次幂
    */
    private boolean is2NPower(int value) {

        int temp = 1;

        if ( value <= 0){
            return false;
        }
        //2^0 = 1
        if (temp == value) {
            return true;
        }

        if (value%2 == 0) {
            return true;
        }

        return false;
    }

    private static boolean is2NPower2(int x){
        boolean result = false;
        if ( x <= 0){ //首先排除负数，可以快速过滤一部分不合格数据。更重要的是过滤特殊数字 0x80000000；
            result = false;
        }
        return result = ((x & (x - 1)) == 0);
    }

    /*4：
    interface IdGenerate{
    long nextID(String name);
    }
    结果：

    a -> 0 ,a -> 1 ,a -> 2........
    b -> 0 ,b -> 1
    c -> 0 ,c -> 1
    意思就是name=a第一次返回0，第二次返回1，第三次返回2，name=b第一次返回0，不同的name是相互独立的。
    写一个类来实现，传一个name获取一个ID，每一个name的ID是相互独立的，要求线程安全，高并发，性能好。
    */
    static class IdGenerate {
        private static final Map<String, AtomicLong> IDMap = new ConcurrentHashMap();

        public static long nextID(String name) {
            AtomicLong atomicLong = IDMap.get(name);
            while (Objects.isNull(atomicLong)) {
                synchronized (IdGenerate.class) {
                    atomicLong = new AtomicLong();
                    IDMap.put(name,atomicLong);
                }
            }

            return atomicLong.incrementAndGet();
        }
    }

    /*5.
    interface Index{

    void  put（int start，int end，array arr）;

    T get（Key key）;

    }

    实例：
    put（0，100，{a,b,c}）;
    get (46)  return {a,b,c}; 就是0-100任意一个数字都可以查找到{a,b,c}

    put(50,200,{f,g});
    get (60)  return {a,b,c,f,g}
    get (40)  return {a,b,c}
    get (150) return {f,g}
    第二次put之后分为三种结果集，0-50，50-100，100-200

    设计一种结构，并实现put，get，要求get方法的时间复杂度低于O(n)
    */


    /*6:
       线程池：  主线程获取到线程池中执行任务时出现的异常

       主线程向线程池提交了1000个任务，任务会返回一个String，在主线程中怎么打印任务返回的String，那个任务先执行完先打印。
    */

    public void threadTest() throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(4);

        List<Future> futureList = new ArrayList(1000);
        for(int i = 0;i<1000;i++) {
            Future<String> f = exec.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    //do something
                    return UUID.randomUUID().toString();
                }
            });
            futureList.add(f);
        }
        while(futureList.size()!= 0) {
            for(Iterator<Future> it =futureList.iterator(); it.hasNext();) {
                Future f = it.next();
                if(f.isDone()) {
                    //这里出抛出InterruptedException和ExecutionException异常要捕获
                    System.out.println(f.get());
                    it.remove();
                }
            }
        }
        exec.shutdown();
    }
}
