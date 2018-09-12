package com.zbq.springiocv1.spring;

import com.zbq.springiocv1.spring.annotation.Autowire;
import com.zbq.springiocv1.spring.annotation.Controller;
import com.zbq.springiocv1.spring.annotation.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangboqing
 * @date 2018/7/10
 */
@WebServlet(urlPatterns = "/*",initParams = {
        @WebInitParam(name = "contextConfigLocation", value = "classpath:application.properties"),
})
public class DispatchServlet extends HttpServlet {

    private Properties contextConfig = new Properties();
    private List<String> classNames = new ArrayList<>();
    private Map<String,Object> beanMap = new ConcurrentHashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(">>>>>>>>调用doPost>>>>>>>>>");
    }


    @Override
    public void init(ServletConfig config) throws ServletException {

        //开始初始化容器

        //1.定位
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //2.加载
        doScanner(contextConfig.getProperty("scanPackage"));

        //3.注册
        doRegister();

        //4.自动注入
        //在spring中是通过getBean方法触发自动注入的
        doAutowired();


        //        DemoAction action = (DemoAction)beanMap.get("demoAction");
//        action.query(null,null,"Tom");

        //如果是SpringMVC会多设计一个HnandlerMapping

        //将@RequestMapping中配置的url和一个Method关联上
        //以便于从浏览器获得用户输入的url以后，能够找到具体执行的Method通过反射去调用
        initHandlerMapping();

    }

    private void initHandlerMapping() {

    }

    private void doAutowired() {

        if (beanMap.isEmpty()) {
            return;
        }

        for(Map.Entry<String,Object> entry:beanMap.entrySet()) {
            Field[] declaredFields = entry.getValue().getClass().getDeclaredFields();
            if (declaredFields.length < 1) {
                continue;
            }

            for (Field field : declaredFields) {
                if (field.isAnnotationPresent(Autowire.class)) {
                    Autowire autowire = field.getAnnotation(Autowire.class);
                    String beanName = autowire.value();
                    if (StringUtils.isEmpty(beanName)) {
                        beanName = field.getType().getName();
                    }

                    field.setAccessible(true);

                    try {
                        field.set(entry.getValue(),beanMap.get(beanName));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }



            }

        }

    }

    private void doRegister() {
        if (CollectionUtils.isEmpty(classNames)) {
            return;
        }


        try {
            for(String className:classNames) {
                Class<?> clazz = Class.forName(className);

                //在spring中是通过多个子方法来处理的
                if (clazz.isAnnotationPresent(Controller.class)) {
                    String beanName = lowerFirstCase(clazz.getSimpleName());

                    //在Spring中在这个阶段不是不会直接put instance，这里put的是BeanDefinition
                    beanMap.put(beanName,clazz.newInstance());
                } else if (clazz.isAnnotationPresent(Service.class)) {
                    Service service = clazz.getAnnotation(Service.class);

                    //默认用类名首字母注入
                    //如果自己定义了beanName，那么优先使用自己定义的beanName
                    //如果是一个接口，使用接口的类型去自动注入

                    //在Spring中同样会分别调用不同的方法 autowritedByName autowritedByType
                    String value = service.value();
                    String beanName = value;
                    if (StringUtils.isEmpty(value)) {
                        beanName = clazz.getSimpleName();
                    }

                    Object instance = clazz.newInstance();
                    beanMap.put(beanName,instance);

                    Class<?>[] interfaces = clazz.getInterfaces();
                    if (interfaces.length < 1) {
                        continue;
                    }

                    for (Class<?> iterClazz : interfaces) {
                        beanMap.put(iterClazz.getName(),instance);
                    }

                } else {
                    continue;
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String lowerFirstCase(String str) {

        char[] chars = str.toCharArray();
        chars[0] += 32;

        return String.valueOf(chars);
    }

    private void doScanner(String scanPackage) {
        URL resource = this.getClass().getClassLoader().getResource(scanPackage.replaceAll("\\.", "/"));
        File file = new File(resource.getFile());

        File[] files = file.listFiles();
        for (File file1 : files) {
            if (file1.isDirectory()) {
                doScanner(scanPackage +"."+ file1.getName());
            } else {
                classNames.add(scanPackage+"."+file1.getName().replace(".class",""));
            }
        }

    }

    private void doLoadConfig(String location) {

        //Spring中是通过Reader定位和加载的
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(location.replace("classpath:", ""));
        try {
            contextConfig.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(resourceAsStream)) {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
