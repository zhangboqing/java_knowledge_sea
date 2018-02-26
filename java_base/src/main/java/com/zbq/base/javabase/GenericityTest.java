package com.zbq.base.javabase;

import com.zbq.bean.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author zhangboqing
 * @date 2017/11/29
 */
public class GenericityTest {

    public <T> T checkObject(T t) {
        Class<T> clazz = (Class<T>) t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> type = field.getType();

//           if (type instanceof String)
        }

        return null;
    }

    @Test
    public void run() throws IllegalAccessException {
        User user = new User();
        user.setUsername("德芙M&M\\'s花生巧克力豆40g（黄袋）");
        user.setAge(11);

        specificSymbolHandle(user);
        System.out.println(user);

    }

    public <T> void specificSymbolHandle(T o) throws IllegalAccessException {
        String regexp = "\'";

        Class<?> clazz = o.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Class<?> type = field.getType();
            if (String.class == type) {
                String strValue = (String) field.get(o);
                if (StringUtils.isNotBlank(strValue)) {
                    field.set(o, strValue.replaceAll(regexp, "’"));
                }
            }
        }
    }

}
