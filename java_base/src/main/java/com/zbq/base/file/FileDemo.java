package com.zbq.base.file;

/**
 * @author zhangboqing
 * @date 2018/7/10
 */
public class FileDemo {


    public static void main(String[] args) {
//        demo1();
//        demo2();

//        demo3();
//        demo4();
    }


    //=====================================================
    //        1.Class.getResource(String path)
    //=====================================================
    //Class.getResource和Class.getResourceAsStream在使用时，路径选择上是一样的。
    //path不以’/'开头时，默认是从此类所在的包下取资源；
    //path  以’/'开头时，则是从ClassPath根下获取；

    private static void demo1() {
        System.out.println(FileDemo.class.getResource(""));
        System.out.println(FileDemo.class.getResource("/"));

        //file:/Users/zhangboqing/Software/MyGithub/java_knowledge_sea/java_base/target/classes/com/zbq/base/file/
        //file:/Users/zhangboqing/Software/MyGithub/java_knowledge_sea/java_base/target/classes/
    }
    private static void demo2() {
        // 当前类(class)所在的包目录
        System.out.println(FileDemo.class.getResource(""));
        // class path根目录
        System.out.println(FileDemo.class.getResource("/"));

        // TestMain.class在demo包中
        // 2.properties  在demo包中
        System.out.println(FileDemo.class.getResource("2.properties"));

        // FileDemo.class在demo包中
        // 3.properties  在demo包中
        System.out.println(FileDemo.class.getResource("demo/3.properties"));

        // FileDemo.class在demo包中
        // 1.properties  在（class根目录）
        System.out.println(FileDemo.class.getResource("/1.properties"));
    }

    //======================================================================
    //        2.Class.getClassLoader（）.getResource(String path)
    //======================================================================
    //Class.getClassLoader（）.getResource和Class.getClassLoader（）.getResourceAsStream在使用时，路径选择上也是一样的
    //path不能以’/'开头时；
    //path是从ClassPath根下获取；

    private static void demo3() {
        FileDemo t = new FileDemo();
        System.out.println(t.getClass());
        System.out.println(t.getClass().getClassLoader());
        System.out.println(t.getClass().getClassLoader().getResource(""));
        System.out.println(t.getClass().getClassLoader().getResource("/"));//null
    }

    private static void demo4() {
        FileDemo t = new FileDemo();
        System.out.println(t.getClass().getClassLoader().getResource(""));

        System.out.println(t.getClass().getClassLoader().getResource("1.properties"));
        System.out.println(t.getClass().getClassLoader().getResource("com/zbq/base/file/2.properties"));
        System.out.println(t.getClass().getClassLoader().getResource("com/zbq/base/file/demo/3.properties"));
    }
}
