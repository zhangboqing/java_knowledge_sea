package com.zbq.controller.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Objects;

/**
 * @author zhangboqing
 * @date 2017/11/21
 */
@RestController
@RequestMapping("/http/protocol")
public class HttpProtocolController {
    Logger logger = LoggerFactory.getLogger(HttpProtocolController.class);


    /**
     * 获取http请求内容
     *
     * 1.HttpServletRequest中用于获取请求行的方法
     * request.getMethod();     //请求方式
     * request.getRequetURI();  //request.getRequetURL()   请求资源
     * request.getProtocol();   //请求http协议版本
     *
     * 2.HttpServletRequest中用于获取请求头的方法
     * request.getHeader("name")   //根据name,获取对应请求头数据
     * request.getHeaderNames()    //获取所有的请求头名称
     *
     * eg:
     *  Host: localhost:8080 （必须的）当前请求访问的目标地址（主机:端口）
     *  Connection: keep-alive 浏览器跟服务器连接状态。close: 连接关闭 keep-alive：保存连接。
     *  Cache-Control: max-age=0 在0秒内不会重新访问服务器，也就是页面立即失效。
     *  Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,/;q=0.8
     *  Upgrade-Insecure-Requests: 1
     *  User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36
     *  DNT: 1
     *  Accept-Encoding: gzip, deflate, sdch 浏览器接受的数据压缩格式
     *  Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.6 浏览器接受的语言
     *  Cookie: 浏览器保存的cookie信息
     *
     * 3.HttpServletRequest中用于获取请求实体内容的方法
     *  对于post方式, request.getInputStream()   //获取实体内容数据
     *  对于get方式, 要获取提交的参数需要使用request.getQueryString();方法,
     *
     *  这样造成API的不一致, 很麻烦. 所以最好使用如下统一方便的获取参数的方式:
     *  request.getParameter("参数名");  //根据参数名获取参数值（注意，只能获取一个值的参数）
     *  request.getParameterValue("参数名“); //根据参数名获取参数值（可以获取多个值的参数）
     *  request.getParameterNames();   //获取所有参数名称列表
     *
     */
    @RequestMapping(value = "/content" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object httpContent(HttpServletRequest request) throws Exception {

        //TODO: 请求头
        logger.info("请求方式：{}", request.getMethod());
        logger.info("请求资源：{}", request.getRequestURI());
        logger.info("请求http协议版本：{}", request.getProtocol());
        logger.info("============>请求行：{} {} {}", request.getMethod(), request.getRequestURI(), request.getProtocol());

        //TODO: 请求行
        System.out.println("\n");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            logger.info("============>请求头：{}:{}", headerName, request.getHeader(headerName));
        }

        //TODO: 请求体 针对post请求
        System.out.println("\n");
        String queryString = request.getQueryString();
        if (Objects.nonNull(queryString)) {
            logger.info("============>get请求参数：{}",queryString);
        }

        InputStream inputStream = request.getInputStream();
        if (Objects.nonNull(inputStream)) {
            //从输入流中读取数据到字符串中   针对 enctype=multipart/form-data
            byte[] b = new byte[1024];
            String res = "";

            int length = 0;
            StringBuilder info = new StringBuilder();
            while ( (length = inputStream.read(b,0,1024)) > 0){
                info.append(new String(b,0,length));
            }


            logger.info("============>post请求参数：{}", info.toString());
        }

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            logger.info("============>请求参数：{}={}",parameterName,request.getParameter(parameterName));
        }

        return "success";
    }


    /**
     * 获取http请求参数方法比较
     *
      一 获取URL:
     getRequestURL()

     二 获取参数列表:

     1.getQueryString()

     只适用于GET,比如客户端发送http://localhost/testServlet?a=b&c=d&e=f,通过request.getQueryString()得到的是a=b&c=d&e=f.


     2.getParameter()
     GET和POST都可以使用
     但如果是POST请求要根据<form>表单提交数据的编码方式来确定能否使用.
     当编码方式是(application/x- www-form-urlencoded)时才能使用.
     这种编码方式(application/x-www-form-urlencoded)虽然简单，但对于传输大块的二进制数据显得力不从心.
     对于传输大块的二进制数这类数据，浏览器采用了另一种编码方式("multipart/form-data"),这时就需要使用下面的两种方法.

     3.getInputStream()
     4.getReader()
     上面两种方法获取的是Http请求包的包体,因为GET方式请求一般不包含包体.所以上面两种方法一般用于POST请求获取参数.

     需要注意的是：
     request.getParameter()、 request.getInputStream()、request.getReader()这三种方法是有冲突的，因为流只能被读一次。
     比如：
     当form表单内容采用 enctype=application/x-www-form-urlencoded编码时，先通过调用request.getParameter()方法得到参数后,
     再调用request.getInputStream()或request.getReader()已经得不到流中的内容，
     因为在调用 request.getParameter()时系统可能对表单中提交的数据以流的形式读了一次,反之亦然。

     当form表单内容采用 enctype=multipart/form-data编码时，即使先调用request.getParameter()也得不到数据，
     所以这时调用request.getParameter()方法对 request.getInputStream()或request.getReader()没有冲突，
     即使已经调用了 request.getParameter()方法也可以通过调用request.getInputStream()或request.getReader()得到表单中的数据,
     而request.getInputStream()和request.getReader()在同一个响应中是不能混合使用的,如果混合使用就会抛异常。
     */
    @RequestMapping(value = "/get/request/param" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object getRequestParam(HttpServletRequest request) throws Exception {


        //TODO getInputStream() / getReader()


        return "success";
    }
}


