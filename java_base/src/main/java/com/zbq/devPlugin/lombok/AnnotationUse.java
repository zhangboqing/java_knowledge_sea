package com.zbq.devPlugin.lombok;

import com.zbq.devPlugin.lombok.bean.ConstructorClass;
import lombok.*;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author zhangboqing
 * @date 2018/6/8
 */
public class AnnotationUse {

    //1.@val
    //val：用在局部变量前面，相当于将变量声明为final
    @Test
    public void annot_1() {
        @val final int a = 10;
        val b = 1;

        Double c = Double.valueOf(a);
        System.out.println(c);
    }

    //2.@NonNull
    //给方法参数增加这个注解会自动在方法内对该参数进行是否为空的校验，如果为空，则抛出NPE（NullPointerException）
    @Test
    public void annot_2() {

        System.out.println(getA(null));
    }

    public Integer getA(@NonNull Integer a) {
        return a;
    }

    //3.@Cleanup
    //自动管理资源，用在局部变量之前，在当前变量范围内即将执行完毕退出之前会自动清理资源，自动生成try-finally这样的代码来关闭流
    @Test
    public void annot_3() throws Exception {
        byte[] buf = new byte[100];
        @Cleanup ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buf);
        byteArrayInputStream.available();

        //等价于
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(buf);
        try {
            byteArrayInputStream2.available();
        } finally {
            try {
                byteArrayInputStream2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //4.@Getter/@Setter
    //用在属性上，再也不用自己手写setter和getter方法了，还可以指定访问范围
    @Test
    public void annot_4() {
        System.out.println(GetSetClass.getUserName());
        System.out.println(GetSetClass.getAge());
    }

    public static class GetSetClass {
        @Getter
        @Setter
        private static String userName = "GetSetClass";
        @Getter
        @Setter
        private static Integer age = 1;
    }


    //5.@ToString
    //用在类上，可以自动覆写toString方法，当然还可以加其他参数，
    // 例如@ToString(exclude=”id”)排除id属性，或者@ToString(callSuper=true, includeFieldNames=true)调用父类的toString方法，包含所有属性
    @Test
    public void annot_5() {
        ToStringClass toStringClass = new ToStringClass();
        System.out.println(toStringClass.toString());
    }

    @ToString(exclude = {"age"}, includeFieldNames = false)
    public class ToStringClass {
        @Getter
        @Setter
        private String userName = "GetSetClass";
        @Getter
        @Setter
        private Integer age = 1;
    }


    //6.@EqualsAndHashCode
    //用在类上，自动生成equals方法和hashCode方法
    @Test
    public void annot_6() {
        EqualsAndHashCodeClass equalsAndHashCodeClass = new EqualsAndHashCodeClass();
        equalsAndHashCodeClass.setAge(1);
        EqualsAndHashCodeClass equalsAndHashCodeClass2 = new EqualsAndHashCodeClass();
        equalsAndHashCodeClass2.setAge(2);
        System.out.println(equalsAndHashCodeClass.equals(equalsAndHashCodeClass2));
    }

    //    @EqualsAndHashCode(onParam =@__({@NonNull}))
    @EqualsAndHashCode(of = {"userName"})
    public class EqualsAndHashCodeClass {
        @Getter
        @Setter
        private String userName = "GetSetClass";
        @Getter
        @Setter
        private Integer age;
    }

    //7.@NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor
    //用在类上，自动生成无参构造和使用所有参数的构造函数以及把所有@NonNull属性作为参数的构造函数，
    //如果指定staticName = “of”参数，同时还会生成一个返回类对象的静态工厂方法，比使用构造函数方便很多
    //用在类上，自动生成equals方法和hashCode方法
    @Test
    public void annot_7() {
        System.out.println(new ConstructorClass());
    }

    //8.@Data
    //注解在类上，相当于同时使用了@ToString、@EqualsAndHashCode、@Getter、@Setter
    // 和@RequiredArgsConstrutor这些注解，对于POJO类十分有用
    @Test
    public void annot_8() {
        System.out.println(new DataClass().getUserName());
    }

    @Data
    public class DataClass {
        private String userName = "GetSetClass";
        private Integer age;
    }

    //9.@Value
    //用在类上，是@Data的不可变形式，相当于为属性添加final声明，只提供getter方法，而不提供setter方法
    @Test
    public void annot_9() {
        System.out.println(new ValueClass(1).getUserName());
    }

    @Value
    public class ValueClass {
        private String userName = "GetSetClass";
        private Integer age;
    }

    //10.@Builder
    //用在类、构造器、方法上，为你提供复杂的builder APIs，让你可以像如下方式一样调用
    // Person.builder().name("Adam Savage").city("San Francisco").job("Mythbusters").job("Unchained Reaction").build();更多说明参考Builder
    @Test
    public void annot_10() {
        System.out.println(BuilderClass.builder().userName("name").age(1).build());
    }

    @Builder
    @Data
    public static class BuilderClass {
        private String userName = "GetSetClass";
        private Integer age;
    }


    //11.@SneakyThrows
    //自动抛受检异常，而无需显式在方法上使用throws语句
    @Test
    public void annot_11() {
        System.out.println(SneakyThrowsClass.builder().build().utf8ToString(new byte[10]));
    }

    @Builder
    @Data
    public static class SneakyThrowsClass {
        private String userName = "GetSetClass";
        private Integer age;

        @SneakyThrows
        public String utf8ToString(byte[] bytes) {
            return new String(bytes, "UTF-8");
        }
    }

    //12.@Synchronized
    // 用在方法上，将方法声明为同步的，并自动加锁，而锁对象是一个私有的属性$lock或$LOCK，
    // 而java中的synchronized关键字锁对象是this，锁在this或者自己的类对象上存在副作用，
    // 就是你不能阻止非受控代码去锁this或者类对象，这可能会导致竞争条件或者其它线程错误
    @Test
    public void annot_12() {
        System.out.println(SynchronizedClass.builder().build().getValue());
    }

    @Builder
    @Data
    public static class SynchronizedClass {
        private String userName = "GetSetClass";
        private Integer age;

        @Synchronized
        public String getValue() {
            return "1";
        }
    }


    //13.@Getter(lazy=true)
    // 可以替代经典的Double Check Lock样板代码
    @Test
    public void annot_13() {
        System.out.println(GetterLazyClass.builder().build().getCached());
    }

    @Builder
    @Data
    public static class GetterLazyClass {
        @Getter(lazy = true)
        private final double[] cached = expensive();
        private double[] expensive() {
            double[] result = new double[1000000];
            for (int i = 0; i < result.length; i++) {
                result[i] = Math.asin(i);
            }
            return result;
        }
    }
}
