package com.zbq.base.javabase.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhangboqing
 * @date 2018/4/8
 */
public class User {

    private Integer a;
    private BigDecimal money;
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

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
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
