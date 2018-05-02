package com.zbq.base.javabase.bean;

import java.util.List;

/**
 * @author zhangboqing
 * @date 2018/4/8
 */
public class User2 {

    private Integer a;
    private Object money;
    private List<Haha> list;

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public List<Haha> getList() {
        return list;
    }

    public void setList(List<Haha> list) {
        this.list = list;
    }

    public Object getMoney() {
        return money;
    }

    public void setMoney(Object money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "a=" + a +
                ", money=" + money +
                ", list=" + list +
                '}';
    }
}
