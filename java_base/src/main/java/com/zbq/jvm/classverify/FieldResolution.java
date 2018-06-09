package com.zbq.jvm.classverify;

public class FieldResolution {
    interface Interface0 {
        int A = 0;

        static int getValue() {
            return A;
        }
    }

    interface Interface1 extends Interface0 {
        int A = 1;
    }

    interface Interface2 {
        int A = 2;
    }

    static class Parent implements Interface1 {
        public int A = 3;
    }

    static class Sub extends Parent implements Interface2 {
        public int A = 4;
    }

    public static void main(String[] args) {

        Sub sub = new Sub();
        Parent sub2 = sub;
        System.out.println(sub.A);
        System.out.println(sub2.A);
    }
}