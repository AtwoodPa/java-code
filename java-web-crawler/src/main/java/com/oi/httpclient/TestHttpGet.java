package com.oi.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author supanpan
 * @date 2024/07/20
 */
public class TestHttpGet {
    public static void main(String[] args) throws URISyntaxException {
        // 无参构造请求
        // httpGet();
        // 带参数构造请求
        httpGetByParams1();
    }

    private static void httpGetByParams1() throws URISyntaxException {
        // 创建HttpClient
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建URI
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("www.baidu.com")
                .setPath("/s")
                .setParameter("wd", "java")
                //.setParameters(new BasicNameValuePair("wd", "java"))
                .build();

        // 创建HttpGet请求
        HttpGet get = new HttpGet(uri);
        CloseableHttpResponse response = null;
        // 发送请求并获取响应
        try {
            response = client.execute(get);
            // 响应正常才处理
            if (response.getStatusLine().getStatusCode() == 200) {
                // 处理响应
                HttpEntity entity = response.getEntity();
                System.out.println(EntityUtils.toString(entity,"UTF-8"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 关闭所有流
            try {
                client.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // 请求成功才关闭response流
            if (response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    private static void httpGet() {
        // 创建HttpClient
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建HttpGet请求
        HttpGet get = new HttpGet("http://www.baidu.com");
        CloseableHttpResponse response = null;
        // 发送请求并获取响应
        try {
            response = client.execute(get);
            // 响应正常才处理
            if (response.getStatusLine().getStatusCode() == 200) {
                // 处理响应
                HttpEntity entity = response.getEntity();
                System.out.println(EntityUtils.toString(entity,"UTF-8"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 关闭所有流
            try {
                client.close();
             } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // 请求成功才关闭response流
            if (response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
