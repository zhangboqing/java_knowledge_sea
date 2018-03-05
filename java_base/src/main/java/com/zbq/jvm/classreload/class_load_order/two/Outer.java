package com.zbq.jvm.classreload.class_load_order.two;

/**
 * 结论：加载一个类时，其内部类不会同时被加载。一个类被加载，当且仅当其某个静态成员（静态域、构造器、静态方法等）被调用时发生。
 */
public class Outer {
    static {  
        System.out.println("load outer class...");  
    }  
      
    //静态内部类  
    static class StaticInner {  
        static {  
            System.out.println("load static inner class...");  
        }  
          
        static void staticInnerMethod() {  
            System.out.println("static inner method...");  
        }  
    }  
          
    public static void main(String[] args) {  
        Outer outer = new Outer();      //此刻其内部类是否也会被加载？  
         System.out.println("===========分割线===========");  
        Outer.StaticInner.staticInnerMethod();      //调用内部类的静态方法  
    }  
}  