package com.oi.httpclient;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * * 测试httpclient
 *
 * @author supanpan
 * @date 2024/07/20
 */
public class TestHttpClient {
    private static final Logger logger = LoggerFactory.getLogger(TestHttpClient.class);

    public static void main(String[] args) throws IOException {
        // 1、打开浏览器 创建Http客户端
        CloseableHttpClient client = HttpClients.createDefault();
        // 2、输入网址
        HttpGet httpGet = new HttpGet("https://www.njmmxx.com");
        // 设置UA信息，模拟浏览器
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        // 3、发送请求
        CloseableHttpResponse response = client.execute(httpGet);
        // 4、服务器响应
        HttpEntity entity = response.getEntity();
        System.out.println(EntityUtils.toString(entity, "utf-8"));
    }
}
