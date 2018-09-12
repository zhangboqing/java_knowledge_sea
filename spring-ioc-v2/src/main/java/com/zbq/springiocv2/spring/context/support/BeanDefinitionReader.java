package com.zbq.springiocv2.spring.context.support;

import com.zbq.springiocv2.spring.beans.BeanDefinition;
import org.springframework.util.Assert;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author zhangboqing
 * @date 2018/7/14
 */
//用来对配置文件进行查找，读取和解析
public class BeanDefinitionReader {

    private Properties config = new Properties();
    private List<String> registryBeanClasses = new ArrayList<>();

    //用来获取配置文件中，自动扫描的包名的key
    private final String SCAN_PACKAGE = "scanPackage";

    public BeanDefinitionReader(String... location) {
        Assert.notNull(location,"location must not be null");
        //通过 try-resource 来释放流
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(location[0].replace("classPath:", ""))) {
            config.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //扫描
        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    //递归扫描包下的所有类，并保存到list中
    private void doScanner(String pachageNamae) {
        URL resource = this.getClass().getClassLoader().getResource(pachageNamae.replaceAll("\\.", "/"));
        File fileDir = new File(resource.getFile());
        for (File file :fileDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(pachageNamae+"."+file.getName());
            } else {
                registryBeanClasses.add(pachageNamae+"."+file.getName().replace(".class",""));
            }
        }
    }

    public Properties getConfig() {
        return config;
    }


    //每注册一个bean，就返回一个beanDefinication
    //只是为了对配置信息进行封装
    public BeanDefinition registryBean(String className) {

        if (registryBeanClasses.contains(className)) {
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setBeanClassName(className);
            beanDefinition.setFactoryBeanName(lowerFirstCase(className.substring(className.lastIndexOf(".")+1)));
            return beanDefinition;
        }

        return null;
    }

    private String lowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;

        return String.valueOf(chars);
    }


    public List<String> loadBeanDefinications() {
        return registryBeanClasses;
    }
}
