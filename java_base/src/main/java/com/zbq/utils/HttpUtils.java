package com.zbq.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 功能 :
 * 基本的http请求
 * get(string参数的方式/map参数的方式)
 * post(string参数的方式/map参数的方式)
 * download文件下载
 * 加密逻辑不包含在此工具类中
 *
 * TODO  回调函数使用
 * TODO  上传
 * TODO  请求缓存使用
 * TODO  并发请求配置
 * @author zhangboqing
 * @date 2018/8/3
 */
@Slf4j
public class HttpUtils {

    private static final MediaType TEXT = MediaType.parse("text/plain; charset=utf-8");
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType DATA = MediaType.parse("application/form-data");

    private static final String TAG_TYPE_GET = "简单的GET请求";
    private static final String TAG_TYPE_DOWNLOAD = "文件下载请求";
    private static final String TAG_TYPE_GET_WITH_SIGN = "包含签名的GET请求";
    private static final String TAG_TYPE_POST_FORM = "FORM表单格式的POST请求";
    private static final String TAG_TYPE_POST_JSON = "JSON格式的POST请求";
    private static final String TAG_TYPE_POST_WITH_SIGN = "包含签名的POST请求";

    public static OkHttpClient createClient(){
        return new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(300,TimeUnit.SECONDS)
                .readTimeout(300,TimeUnit.SECONDS)
                .build();
    }

    /**最简单get请求,只有url地址,如果要追加参数,可以直接在url地址后边拼接**/
    public static String get(String url){

        validRequestParams(url);

        Request request = new Request.Builder()
                .tag(TAG_TYPE_GET)
                .url(url)
                .build();

        log.debug(request.toString());

        return handleSimpleResponse(request);
    }

    /**最简单get请求,只有url地址,如果要追加参数,可以直接在url地址后边拼接**/
    public static String get(String url, String params){

        validRequestParams(url);

        Request request = new Request.Builder()
                .tag(TAG_TYPE_GET)
                .url(url + "?" + params)
                .build();

        log.debug(request.toString());

        return handleSimpleResponse(request);
    }

    /**
     * 1.基本参数,url地址
     */
    public static String get(String url, Map<String,Object> params){

        validRequestParams(url);

        if(CollectionUtils.isEmpty(params)){
            handleException("请求参数params不能为空");
        }

        StringBuffer sb = new StringBuffer();
        for ( Map.Entry<String,Object> entry : params.entrySet() ) {
            //这里不管参数是否为空,都原样翻译成字符串,不做特殊处理
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }

        Request request = new Request.Builder()
                .tag(TAG_TYPE_GET)
                .url(url+"?"+sb.toString())
                .build();

        log.debug(request.toString());

        return handleSimpleResponse(request);
    }


    /**
     * 附带Header的get请求
     */
    public static String get(String url, Map<String,Object> params , Map<String,?> headerParams){

        validRequestParams(url);

        StringBuffer sb = null;

        if(params != null && params.size() > 0 ){
            sb = new StringBuffer();
            for ( Map.Entry<String,Object> entry : params.entrySet() ) {
                //这里不管参数是否为空,都原样翻译成字符串,不做特殊处理
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.tag(TAG_TYPE_GET);
        if(sb != null) {
            requestBuilder.url(url + "?" + sb.toString());
        }else{
            requestBuilder.url(url);
        }
        handleHeaders(headerParams, requestBuilder);

        Request request = requestBuilder.build();

        log.debug(request.toString());

        return handleSimpleResponse(request);
    }


    /**带参数的请求,通过form表单传递参数**/
    public static String getIncludeHeader(String url,Map<String,Object> requestParams, Map<String,?> headerParams){

        validRequestParams(url);

        //封装请求参数
        StringBuffer sb = new StringBuffer();
        if(!CollectionUtils.isEmpty(requestParams)){
            for ( Map.Entry<String,Object> entry : requestParams.entrySet() ) {
                //这里不管参数是否为空,都原样翻译成字符串,不做特殊处理
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }

        Request.Builder requestBuilder = new Request.Builder();
        //必传参数
        requestBuilder.tag(TAG_TYPE_GET).url(url+"?"+sb.toString());

        //请求header
        handleHeaders(headerParams, requestBuilder);

        Request request = requestBuilder.build();
        log.debug(request.toString());

        return handleSimpleResponse(request);
    }

    /**最简单post请求,只有url地址**/
    public static String post(String url){
        return post(url,null,null);
    }

    /**带参数的请求,通过form表单传递参数**/
    public static String post(String url, Map<String,?> requestParams){
        return post(url,requestParams,null);
    }

    /**带参数的请求,通过form表单传递参数**/
    public static String post(String url, Map<String,?> requestParams, Map<String,?> headerParams){

        validRequestParams(url);

        Request.Builder requestBuilder = new Request.Builder();
        //必传参数
        requestBuilder.tag(TAG_TYPE_POST_FORM).url(url);

        //请求header
        handleHeaders(headerParams, requestBuilder);

        //请求参数form
        handleParams(requestParams, requestBuilder);

        Request request = requestBuilder.build();

        if (!CollectionUtils.isEmpty(requestParams)) {
            log.debug("http请求参数====>"+requestParams);
        }
        log.debug(request.toString());

        return handleSimpleResponse(request);
    }

    /**
     * json方式请求
     * @param url url地址
     * @param requestParams json字符串
     * @return
     */
    public static String postJson(String url, String requestParams){
        return postJson(url,requestParams,null);
    }

    /**
     *
     * @param url url地址
     * @param requestParams json字符串
     * @param headerParams 请求头信息
     * @return
     */
    public static String postJson(String url, String requestParams, Map<String,Object> headerParams){

        validRequestParams(url,requestParams);

        Request.Builder requestBuilder = new Request.Builder();
        //必传参数
        requestBuilder.tag(TAG_TYPE_POST_JSON).url(url);

        //请求header
        handleHeaders(headerParams, requestBuilder);

        //请求参数form
        RequestBody body = RequestBody.create(JSON,requestParams);

        requestBuilder.post(body);

        Request request = requestBuilder.build();
        if (!StringUtils.isEmpty(requestParams)) {
            log.debug("http请求参数====>"+requestParams);
        }
        log.debug(request.toString());

        return handleSimpleResponse(request);
    }

    /**
     * 带参数的请求,通过form表单传递参数
     * @param url 请求地址
     * @param requestParams 请求参数
     * @param headerParams 请求头信息
     * @return
     */
    public static String postJson(String url, Map<String,Object> requestParams, Map<String,Object> headerParams){

        validRequestParams(url);

        Request.Builder requestBuilder = new Request.Builder();
        //必传参数
        requestBuilder.tag(TAG_TYPE_POST_JSON).url(url);
        //请求header
        handleHeaders(headerParams, requestBuilder);

        //请求参数form
        RequestBody body = RequestBody.create(JSON, JSONObject.toJSONString(requestParams));

        requestBuilder.post(body);

        Request request = requestBuilder.build();

        log.debug(request.toString());

        return handleSimpleResponse(request);
    }

    /**文件下载**/
    public static byte[] download(String url, Map<String,Object> params){

        validRequestParams(url);

        if(CollectionUtils.isEmpty(params)){
            handleException("请求参数params不能为空");
        }

        StringBuffer sb = new StringBuffer();
        for ( Map.Entry<String,Object> entry : params.entrySet() ) {
            //这里不管参数是否为空,都原样翻译成字符串,不做特殊处理
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }

        Request request = new Request.Builder()
                .tag(TAG_TYPE_DOWNLOAD)
                .url(url + "?" + sb.toString())
                .build();

        log.debug(request.toString());

        return handleStreamResponse(request);
    }

    /**文件上传**/


    /*
     ****************************************私有方法区*******************************************
                   _               _                           _    _                 _
                  (_)             | |                         | |  | |               | |
      _ __   _ __  _ __   __ __ _ | |_  ___   _ __ ___    ___ | |_ | |__    ___    __| |
     | '_ \ | '__|| |\ \ / // _` || __|/ _ \ | '_ ` _ \  / _ \| __|| '_ \  / _ \  / _` |
     | |_) || |   | | \ V /| (_| || |_|  __/ | | | | | ||  __/| |_ | | | || (_) || (_| |
     | .__/ |_|   |_|  \_/  \__,_| \__|\___| |_| |_| |_| \___| \__||_| |_| \___/  \__,_|
     | |
     |_|
     ****************************************私有方法区*******************************************
     */


    private static void validRequestParams(String url, String ... params) {

        if( StringUtils.isEmpty(url) ){
            handleException("请求URL地址不能为空");
            return;
        }

    }

    private static void handleHeaders(Map<String, ?> headerParams, Request.Builder requestBuilder) {
        if( !CollectionUtils.isEmpty(headerParams) ){
            Headers.Builder headerBuilder = new Headers.Builder();
            for ( Map.Entry<String,?> entry : headerParams.entrySet() ) {
                headerBuilder.add(entry.getKey(),(String)entry.getValue());
            }
            requestBuilder.headers(headerBuilder.build());
        }
    }

    private static void handleParams(Map<String, ?> requestParams, Request.Builder requestBuilder) {
        if( !CollectionUtils.isEmpty(requestParams) ){
            FormBody.Builder FormBuilder = new FormBody.Builder();
            for ( Map.Entry<String,?> entry : requestParams.entrySet() ) {
                FormBuilder.add(entry.getKey(),entry.getValue().toString());
            }
            requestBuilder.post(FormBuilder.build());
        }
    }

    /**对结果集的最简单处理**/
    private static String handleSimpleResponse(Request request) {

        OkHttpClient client = createClient();
        try (Response response = client.newCall(request).execute()) {
            String responseResult = response.body().string();
            log.debug("http响应结果====>"+responseResult);
            return responseResult;
        }catch ( Throwable e ){
            log.error("{},{}",e.getMessage(),e);
            throw new RuntimeException(e);
        }

    }

    private static byte[] handleStreamResponse(Request request) {
        OkHttpClient client = createClient();
        try {
            Response response = client.newCall(request).execute();
            String contentType = response.header("Content-Type");
            if( StringUtils.isEmpty(contentType) ){ return new byte[]{0}; }
            if( contentType.contains(DATA.subtype()) ){
                return response.body().bytes();
            }
            // 下载逻辑里边,只要出现json格式的数据,一定是异常信息
            if( contentType.contains(JSON.subtype()) ){
                Map result = JSONObject.parseObject(response.body().string(),Map.class);
                Object message = result.get("message");
                throw new RuntimeException(message.toString());
            }
            // 非json格式的异常,兼容非json格式的错误返回
            if( contentType.contains(TEXT.subtype()) ){
                String result = response.body().string();
                throw new RuntimeException(result);
            }
            return new byte[]{0};
        }catch ( Throwable e ){
            log.error("{},{}",e.getMessage(),e);
            throw new RuntimeException(e);
        }
    }

    /**对异常的特殊处理**/
    private static void handleException(String errorInfo) {
        log.error(errorInfo);
        throw new RuntimeException(errorInfo);
    }

}