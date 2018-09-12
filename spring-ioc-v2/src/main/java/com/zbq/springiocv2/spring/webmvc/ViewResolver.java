package com.zbq.springiocv2.spring.webmvc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhangboqing
 * @date 2018/7/15
 */
//设计这个类的主要目的是：
//1、讲一个静态文件变为一个动态文件
//2、根据用户传送参数不同，产生不同的结果
//最终输出字符串，交给Response输出
public class ViewResolver {

    private String viewName;
    private File templateFile;

    public ViewResolver(String viewName, File templateFile) {
        this.viewName = viewName;
        this.templateFile = templateFile;
    }


    public String viewResolver(ModelAndView mv) {
        StringBuilder sb = new StringBuilder();

        try (RandomAccessFile ra = new RandomAccessFile(templateFile, "r")) {
            String line;
            while ((line = ra.readLine()) != null) {
                line = new String(line.getBytes("ISO-8859-1"), "utf-8");
                Matcher matcher = matcher(line);
                while (matcher.find()) {
                    for (int i = 1; i < matcher.groupCount(); i++) {
                        String paramName = matcher.group(i);
                        Object paramValue = mv.getModel().get(paramName);
                        if (paramValue == null) {
                            continue;
                        }

                        line = line.replaceAll("￥\\{" + paramName + "\\}", paramValue.toString());
                        line = new String(line.getBytes("utf-8"), "ISO-8859-1");
                    }
                    sb.append(line);
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    private Pattern pattern = Pattern.compile("￥\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
    private Matcher matcher(String str) {
        Matcher matcher = pattern.matcher(str);
        return matcher;
    }


    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
}
