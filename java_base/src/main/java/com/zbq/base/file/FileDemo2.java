package com.zbq.base.file;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author zhangboqing
 * @date 2018/9/14
 * <p>
 * 读取文件中的内容
 */
public class FileDemo2 {


    @Test
    public void run() throws IOException {

        //相对路径 相对于项目名来说的
        System.out.println(readFileContent1("src/main/java/com/zbq/base/file/data.txt"));
        //绝对路径
        System.out.println(readFileContent1("/Users/zhangboqing/Software/MyGithub/java_knowledge_sea/java_base/src/main/java/com/zbq/base/file/data.txt"));

    }


    private static String readFileContent1(String fileName) throws IOException {

        File file = new File(fileName);

        StringBuilder sb = new StringBuilder();
        try (FileReader fileReader = new FileReader(file); BufferedReader bf = new BufferedReader(fileReader)) {
            String content = "";
            while (content != null) {
                content = bf.readLine();
                if (content == null) {
                    break;
                }
                sb.append(content.trim());
            }
        }

        return sb.toString();
    }
}
