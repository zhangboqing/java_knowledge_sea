/**
 * @author zhangboqing
 * @date 2018/3/5
 *
 * java8 新特性
 */
package com.zbq.javaNewFeature.java8;


/** 1.接口中的默认方法和静态方法 */
/*
 1.默认方法就像一个普通Java方法，只是方法用default关键字修饰
 public interface Person {
    //默认方法
    default String getName(String name) {
        return name;
    }
}

注意：考虑如果接口中定义了一个默认方法，而另外一个父类或者接口中又定义了一个同名的方法，该选择哪个？
    1) 选择父类中的接口。如果一个父类提供了具体的实现方法，那么接口中具有相同名称和参数的默认方法会被忽略。类优先;
    2) 接口冲突。如果一个父接口提供了一个默认方法，而另一个接口也提供了具有相同名称和参数类型的方法（不管该方法是否是默认方法），那么必须通过覆盖方法来解决。

  2.静态方法就像一个普通Java静态方法，但方法的权限修饰只能是public或者不写
*/

/** 2.函数式接口和Lambda表达式 */
/*
  1.函数式接口
    函数式接口（Functional Interface）是只包含一个方法的抽象接口。
    比如Java标准库中的java.lang.Runnable，java.util.concurrent.Callable就是典型的函数式接口。

在Java 8中通过@FunctionalInterface注解，将一个接口标注为函数式接口，该接口只能包含一个抽象方法。

@FunctionalInterface注解不是必须的，只要接口只包含一个抽象方法，虚拟机会自动判断该接口为函数式接口。

一般建议在接口上使用@FunctionalInterface注解进行声明，以免他人错误地往接口中添加新方法，如果在你的接口中定义了第二个抽象方法的话，编译器会报错。

@FunctionalInterface
public interface Comparator<T> {
int compare(T o1, T o2);
}

函数式接口是为Java 8中的lambda而设计的，lambda表达式的方法体其实就是函数接口的实现。
  2.Lambda表达式
    1）lambda表达式实际上就是代码块的传递的实现。其语法结构如下：

(parameters) -> expression 或者 (parameters) -> {statements;}
括号里的参数可以省略其类型，编译器会根据上下文来推导参数的类型，你也可以显式地指定参数类型，如果没有参数，括号内可以为空。
方法体，如果有多行功能语句用大括号括起来，如果只有一行功能语句则可以省略大括号。

eg:
    new Thread(() -> {
            for (int i = 0; i < 100; i++)
                System.out.println("Lambda Expression");
        }).start();

    Comparator<String> c = (s1, s2) -> Integer.compare(s1.length(), s2.length());

    button.addActionListener(e -> System.out.println("Lambda Expression"));

可以看到lambda表达式使代码变得简单，代替了匿名内部类


    2）方法引用，方法引用是lambda表达式的一种简写形式
    使用“::”操作符将方法名和对象或类的名字分隔开来。以下是四种使用情况：

    对象::实例方法
    类::静态方法
    类::实例方法
    类::new

    Arrays.sort(strings, String::compareToIgnoreCase);
    // 等价于
    Arrays.sort(strings, (s1, s2) -> s1.compareToIgnoreCase(s2));
*/

/** 3.Stream API */
/*
1.Stream的创建方法。
    // 1. Individual values
    Stream stream = Stream.of("a", "b", "c");
    // 2. Arrays
    String [] strArray = new String[] {"a", "b", "c"};
    stream = Stream.of(strArray);
    stream = Arrays.stream(strArray);
    // 3. Collections
    List<String> list = Arrays.asList(strArray);
    stream = list.stream();

2.中间操作包括：map (mapToInt, flatMap 等)、 filter、distinct、sorted、peek、limit、skip、parallel、sequential、unordered。

3.终止操作包括：forEach、forEachOrdered、toArray、reduce、collect、min、max、count、anyMatch、allMatch、noneMatch、findFirst、findAny、iterator。

关于Stream的每个方法如何使用就不展开了，更详尽的介绍看这篇文章：https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/

*/

/** 4.新的日期和时间 API */
/*
Java8 引入了一个新的日期和时间API，位于java.time包下。
新的日期和时间API借鉴了Joda Time库，其作者也为同一人，但它们并不是完全一样的，做了很多改进。
*/

/** 5.杂项改进 */
/*
 1.Java8在String类中只添加了一个新方法，就是join，该方法实现了字符串的拼接，可以把它看作split方法的逆操作。
 String joined = String.join(".", "www", "cnblogs", "com");
 System.out.println(joined); // www.cnblogs.com

 2.数字包装类提供了BYTES静态方法，以byte为单位返回长度。
 3.所有八种包装类都提供了静态的hashCode方法。
 
*/

/** 6.Optional 类*/
/*
    Optional 类已经成为 Java 8 类库的一部分，用来解决空指针异常

*/