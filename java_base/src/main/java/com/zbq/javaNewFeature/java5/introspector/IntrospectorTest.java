package com.zbq.javaNewFeature.java5.introspector;

import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
  
public class IntrospectorTest {
  
    @Test  
    public void tes1() throws Exception {  
        Class<?> cl = Class.forName("com.zbq.javaNewFeature.java5.introspector.Person");
        // 在bean上进行内省  
        BeanInfo beaninfo = Introspector.getBeanInfo(cl, Object.class);  
        PropertyDescriptor[] pro = beaninfo.getPropertyDescriptors();  
        Person p = new Person();  
        System.out.print("Person的属性有:");  
        for (PropertyDescriptor pr : pro) {  
            System.out.print(pr.getName() + " ");  
        }  
        System.out.println("");  
        for (PropertyDescriptor pr : pro) {  
            // 获取beal的set方法  
            Method writeme = pr.getWriteMethod();  
            if (pr.getName().equals("name")) {  
                // 执行方法  
                writeme.invoke(p, "xiong");  
            }  
            if (pr.getName().equals("age")) {  
                writeme.invoke(p, 23);  
            }  
            // 获取beal的get方法  
            Method method = pr.getReadMethod();  
            System.out.println(method.invoke(p) + " ");
  
        }  
    }  
  
    @Test  
    public void test2() throws Exception {  
        PropertyDescriptor pro = new PropertyDescriptor("name", Person.class);  
        Person preson=new Person();  
        Method  method=pro.getWriteMethod();  
        method.invoke(preson, "xiong");  
        System.out.println(pro.getReadMethod().invoke(preson));  
    }  
}  