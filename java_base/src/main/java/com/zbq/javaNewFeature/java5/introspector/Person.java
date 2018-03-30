package com.zbq.javaNewFeature.java5.introspector;

public class Person {

    private int age;
    private String name;  
    public String getName() {
        return name;  
    }  
    public void setName(String name) {
        this.name = name;  
    }  
    public int getAge() {
        return age;  
    }  
    public void setAge(int age) {
        this.age = age;
    }


    public void run() {
        System.out.println("111");
    }

  
}  