package com.zbq.base.agency;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhangboqing
 * @date 2018/4/1
 */
public class ProxyFactory<T> {

    private WorkerImpl worker;

    public T getWorkerProxy(WorkerImpl worker) {
      return (T) Proxy.newProxyInstance(worker.getClass().getClassLoader(), worker.getClass().getInterfaces(),
                (Object proxy, Method method, Object[] args) -> {

                    long startTime = System.currentTimeMillis();
                    Object result =  method.invoke(worker, args);
                    long endTime = System.currentTimeMillis();

                    System.out.println("工作了"+(endTime-startTime)+"ms");

                    return result;
                });
    }


    public static void main(String[] args) {

        WorkerImpl worker = new WorkerImpl();
        ProxyFactory<Worker> workerProxyFactory = new ProxyFactory<>();
        Worker workerProxy = workerProxyFactory.getWorkerProxy(worker);
        workerProxy.work();
    }
}
