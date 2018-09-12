package com.zbq.springiocv2.spring.context;


import com.zbq.springiocv2.spring.annotation.Autowire;
import com.zbq.springiocv2.spring.annotation.Controller;
import com.zbq.springiocv2.spring.annotation.Service;
import com.zbq.springiocv2.spring.aop.AopConfig;
import com.zbq.springiocv2.spring.beans.BeanDefinition;
import com.zbq.springiocv2.spring.beans.BeanPostProcessor;
import com.zbq.springiocv2.spring.beans.BeanWrapper;
import com.zbq.springiocv2.spring.context.support.BeanDefinitionReader;
import com.zbq.springiocv2.spring.core.BeanFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhangboqing
 * @date 2018/7/14
 */
public class ApplicationContext extends DefaultListableBeanFactory implements BeanFactory {


    private String[] configLocations;

    private BeanDefinitionReader reader;

    //用来保证注册式单例的容器
    private Map<String,Object> beanCacheMap = new HashMap<>();

    //用来存储所有被代理的对象
    private Map<String,BeanWrapper> beanWrapperMap = new ConcurrentHashMap<>();

    public ApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    private void refresh() {

        //定位
        this.reader = new BeanDefinitionReader(configLocations);
        //加载
        List<String> beanDefinications =  reader.loadBeanDefinications();
        //注册
        doRegistery(beanDefinications);

        //依赖注入（lazy-init = false），要是执行依赖注入
        //在这里自动调用getBean方法
        doAutowire();

    }

    //开始自动化的依赖注入
    private void doAutowire() {

        for(Map.Entry<String,BeanDefinition> entry :beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();

            //不是懒加载的话，就去加载
            if (!entry.getValue().isLazyInit()) {
                getBean(beanName);
            }
        }

        for(Map.Entry<String, BeanWrapper> entry:beanWrapperMap.entrySet()) {
            populateBean(entry.getKey(),entry.getValue().getOriginalInstance());
        }

    }

    private void populateBean(String beanName, Object originalInstance) {

        Class<?> clazz = originalInstance.getClass();

        if (!(clazz.isAnnotationPresent(Controller.class) || clazz.isAnnotationPresent(Service.class))) {
         return;
        }

        Field[] fields = clazz.getFields();
        if (Objects.isNull(fields)) {
            return;
        }

        for (Field field : fields) {
            if (field.isAnnotationPresent(Autowire.class)) {
                Autowire autowire = field.getAnnotation(Autowire.class);
                String autowireBeanName = autowire.value();
                if (StringUtils.isEmpty(autowireBeanName)) {
                    autowireBeanName = field.getType().getName();
                }

                field.setAccessible(true);

                try {
                    field.set(originalInstance,beanWrapperMap.get(autowireBeanName).getWrapperInstance());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //真正的将BeanDefinitions注册到beanDefinitionMap中
    private void doRegistery(List<String> beanDefinications) {
        if (CollectionUtils.isEmpty(beanDefinitionMap)) {
            return;
        }

        //beanName有三种情况:
        //1、默认是类名首字母小写
        //2、自定义名字
        //3、接口注入
        for (String className : beanDefinications) {
            try {
                Class<?> clazz = Class.forName(className);
                //如果是一个接口，是不能实例化的
                //用它实现类来实例化
                if (clazz.isInterface()) {
                    continue;
                }

                BeanDefinition beanDefinition = reader.registryBean(className);
                if (Objects.nonNull(beanDefinications)) {
                    beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
                }

                Class<?>[] interfaces = clazz.getInterfaces();
                if (Objects.nonNull(interfaces)) {
                    for (Class<?> iter : interfaces) {
                        //如果是多个实现类，只能覆盖
                        //为什么？因为Spring没那么智能，就是这么傻
                        //这个时候，可以自定义名字
                        beanDefinitionMap.put(iter.getName(),beanDefinition);
                    }
                }

                //到这里容器初始化完成
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }


    }

    //依赖注入，从这里开始，通过读取BeanDefinition中的信息
    //然后，通过反射机制创建一个实例并返回
    //Spring做法是，不会把最原始的对象放出去，会用一个BeanWrapper来进行一次包装
    //装饰器模式：
    //1、保留原来的OOP关系
    //2、我需要对它进行扩展，增强（为了以后AOP打基础
    @Override
    public Object getBean(String beanName) {

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        String className = beanDefinition.getBeanClassName();

        //生成事件通知
        BeanPostProcessor beanPostProcessor = new BeanPostProcessor();

        Object instance = instantionBean(beanDefinition);
        if (Objects.isNull(instance)) {
            return null;
        }

        //在实例化之前调用一次
        beanPostProcessor.postProcessBeforeInitialization(instance,beanName);

        BeanWrapper beanWrapper = new BeanWrapper(instance);
        beanWrapper.setBeanPostProcessor(beanPostProcessor);
        beanWrapper.setAopConfig(instantionAopConfig(beanDefinition));
        beanWrapperMap.put(beanName,beanWrapper);

        //在实例化之后调用一次
        beanPostProcessor.postProcessAfterInitialization(instance,beanName);


        //通过这样一调用，相当于给我们自己留有了可操作的空间
        return beanWrapperMap.get(beanName).getWrapperInstance();
    }

    private AopConfig instantionAopConfig(BeanDefinition beanDefinition) {
        AopConfig aopConfig = new AopConfig();

        String pointCut = reader.getConfig().getProperty("pointCut");
        String[] before = reader.getConfig().getProperty("aspectBefore").split("\\s");
        String[] after = reader.getConfig().getProperty("aspectAfter").split("\\s");

        String className = beanDefinition.getBeanClassName();
        try {
            Class<?> clazz = Class.forName(className);
            Method[] methods = clazz.getMethods();
            Pattern compile = Pattern.compile(pointCut);

            Class<?> aspectClass = Class.forName(before[0]);

            if (Objects.nonNull(methods)) {
                //在这里得到的方法都是原生的方法
                for (Method method : methods) {
                    //public .* com\.zbq\.springiocv2\.demo\.service\..*Service\..*\(.*\)
                    //public java.lang.String com.zbq.springiocv2.demo.service.impl.ModifyService.add(java.lang.String,java.lang.String)
                    Matcher matcher = compile.matcher(method.getName());
                    if (matcher.matches()) {
                        //能满足切面规则的类，添加的AOP配置中
                        aopConfig.put(method,aspectClass,new Method[]{aspectClass.getMethod(before[1]),aspectClass.getMethod(after[1])});
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        return aopConfig;
    }

    private Object instantionBean(BeanDefinition beanDefinition) {
        String beanClassName = beanDefinition.getBeanClassName();

        if (beanCacheMap.containsKey(beanClassName)) {
            return beanCacheMap.get(beanClassName);
        } else {
            try {
                Class<?> clazz = Class.forName(beanClassName);
                Object instance = clazz.newInstance();
                beanCacheMap.put(beanClassName,instance);
                return instance;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    public Properties getConfig() {
        return this.reader.getConfig();
    }
}
