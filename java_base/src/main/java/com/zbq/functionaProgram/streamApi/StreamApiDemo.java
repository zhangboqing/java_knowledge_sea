package com.zbq.functionaProgram.streamApi;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangboqing
 * @date 2018/3/4
 */
public class StreamApiDemo {


    @Test
    public void run() {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);

        List<Integer> collect = integers.stream().mapToInt(x -> x + 1).boxed().collect(Collectors.toList());
        for (Integer integer : collect) {

            System.out.println(integer);

        }

    }

    /**
     * list to map
     */
    @Test
    public void run2() {
        ArrayList<User> userList = new ArrayList<>();
        User user = new User();
        user.setAge(1);
        user.setUsername("a");

        userList.add(user);


        Map<String, Integer> map = userList.stream().collect(Collectors.toMap(User::getUsername, User::getAge));
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {

            System.out.println(entry.getKey());

        }


    }


    @Test
    public void run3() {
        Integer[] arr = {1, 2, 3};
        Stream<Integer> stream = Stream.of(arr);

        Stream.iterate(0, (x) -> x + 1).limit(3).forEach((x) -> System.out.println(x));

    }

    @Test
    public void run5() {
        int n = 20;

        //生成0-n的数列用来表示  fabonacci数列的下标
        Stream.iterate(0, (x) -> x + 1).limit(n)
                //映射获取fabonacci数
                .map((x)->
            fabonacci(x)
        )
                //遍历打印
                .forEach((x)-> System.out.println(x));

    }

    @Test
    public void run6() {

        Map<Boolean, List<Integer>> collect = Stream.iterate(0, (x) -> x + 1).limit(10).collect(Collectors.partitioningBy((x) -> {
            return x % 2 == 0;
        }));

        for (Boolean aBoolean : collect.keySet()) {
            List<Integer> integers = collect.get(aBoolean);
            integers.stream().forEach((x)-> System.out.println(x));
        }


        Map<Integer, List<Integer>> collect1 = Stream.generate(()->{return 1;}).limit(10).collect(Collectors.groupingBy((x) -> {
            return x;
        }));

        for (Integer integer : collect1.keySet()) {
            List<Integer> integers = collect1.get(integer);
            integers.stream().forEach((x)-> System.out.println(x));
        }


    }

    public int fabonacci(int n) {

        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        return fabonacci(n - 1) + fabonacci(n-2);
    }


    /*使用Java 8 的函数式编程，产生一个Fibonacci序列。给出你的注释，为什么要这么实现*/
    @Test
    public void run4() {
        ArrayList<Integer> arr = new ArrayList<>();
        Integer reduce = arr.stream().reduce(0, (x, y) -> x + y);
        System.out.println(reduce);


    }


    public class User {
        private String username;
        private Integer age;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
