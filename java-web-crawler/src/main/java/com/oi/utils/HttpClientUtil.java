package com.oi.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * HttpClient工具类
 *
 * @author supanpan
 * @date 2024/07/20
 */
public class HttpClientUtil {
    private static PoolingHttpClientConnectionManager connectionManager;
    private static RequestConfig config;

    /**
     * 获取httpclient
     * @return httpclient
     */
    public static CloseableHttpClient getHttpClient() {
        if (connectionManager == null) {
            // 创建连接池
            connectionManager = new PoolingHttpClientConnectionManager();
            // 连接池配置
            // 设置最大连接数
            connectionManager.setMaxTotal(100);
            // 设置每个路由的最大连接数
            connectionManager.setDefaultMaxPerRoute(20);
        }
        if (config == null){
            // 配置连接池中连接的参数
            config = RequestConfig.custom()
                    .setConnectTimeout(5000)// 发送请求的超时时间
                    .setSocketTimeout(2000)// 从服务器获取响应数据的超时时间
                    .setConnectionRequestTimeout(500)// 从连接池获取连接的超时时间
                    .build();
        }
        // 创建HttpClient
        return HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(config)
                // 设置代理
//                .setProxy(new HttpHost("121.41.54.26", 80))
                .build();
    }

    /**
     * 获取get请求结果
     * @param url
     * @return
     */
    public static String doGet(String url){
        String result = null;
        // 获取client
        CloseableHttpClient client = getHttpClient();
        // 创建HttpGet请求
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = null;
        // 发送请求并获取响应
        try {
            response = client.execute(get);
            // 响应正常才处理
            if (response.getStatusLine().getStatusCode() == 200) {
                // 处理响应
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity,"UTF-8");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 请求成功才关闭response流
            if (response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }

    /**
     * 获取post请求结果
     * @param url
     * @return
     */
    public static String doPost(String url){
        String result = null;
        // 获取client
        CloseableHttpClient client = getHttpClient();
        // 创建HttpPost请求
        HttpPost post = new HttpPost(url);
        CloseableHttpResponse response = null;
        // 发送请求并获取响应
        try {
            response = client.execute(post);
            // 响应正常才处理
            if (response.getStatusLine().getStatusCode() == 200) {
                // 处理响应
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity,"UTF-8");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 请求成功才关闭response流
            if (response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }
}
