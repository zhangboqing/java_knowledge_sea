package com.zbq.javaNewFeature.java7;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * try-with-resource
 *
 * try{资源申明}
 * 资源申明的注意事项：
 * 1.声明的变量的类型必须是AutoCloseable的子类型，否则会发生编译时错误
 *
 */
public class AutoCloseableDemo {
    public static void main(String[] args) {
        try (AutoCloseableObjecct app = new AutoCloseableObjecct()) {
            System.out.println("--执行main方法--");
        } catch (Exception e) {
            System.out.println("--exception--");
        } finally {
            System.out.println("--finally--");
        }
    }


    //自己定义类 没有实现AutoCloseable
    public static class Objecct  {

    }

    //自己定义类 并实现AutoCloseable
    public static class AutoCloseableObjecct implements AutoCloseable {
        @Override
        public void close() throws Exception {
            System.out.println("--AutoCloseableObjecct close--");
        }

    }


    //自己定义类 并实现AutoCloseable
    public static class AutoCloseableObjecct2 implements AutoCloseable {
        private AutoCloseableObjecct autoCloseableObjecct;

        public AutoCloseableObjecct2(AutoCloseableObjecct autoCloseableObjecct) {
            this.autoCloseableObjecct = autoCloseableObjecct;
        }

        @Override
        public void close() throws Exception {
            System.out.println("--AutoCloseableObjecct2 close--");
        }

    }


    //注意：利用try-with-resource时，嵌套创建的对象，最外层的对象能自动释放，里面的对象是不会自动释放的
    @Test
    public void run() throws Exception {
        try (AutoCloseableObjecct2 autoCloseableObjecct2 = new AutoCloseableObjecct2(new AutoCloseableObjecct())) {

            System.out.println("1");
        }
    }

    @Test
    public void run2() throws Exception {
        try (AutoCloseableObjecct autoCloseableObjecct = new AutoCloseableObjecct();AutoCloseableObjecct2 autoCloseableObjecct2 = new AutoCloseableObjecct2(autoCloseableObjecct)) {

            System.out.println("2");
        }
    }


    @Test
    public void demo2() {

        //JDK1.7之前,释放资源方式
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //1.7之后，只要实现了AutoCloseable接口
        try (FileInputStream fileInputStream2 = new FileInputStream("")) {

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}