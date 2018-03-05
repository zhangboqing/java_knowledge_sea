package com.zbq.functionaProgram.streamApi;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    /** list to map */
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
