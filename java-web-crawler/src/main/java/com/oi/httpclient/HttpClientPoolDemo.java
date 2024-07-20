package com.oi.httpclient;

import com.oi.utils.HttpClientUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * HttpClient连接池
 *
 * @author supanpan
 * @date 2024/07/20
 */
public class HttpClientPoolDemo {
    public static void main(String[] args) {
        System.out.println("HttpClientUtil.doGet(\"http://www.baidu.com\") = " + HttpClientUtil.doGet("http://www.baidu.com"));
//        try {
//            // poolParams();
//            setPoolProxy();
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
    }

    /**
     * 创建池，配置代理
     * 代理网址：http://www.ip3366.net/
     * @throws URISyntaxException
     */
    private static void setPoolProxy() throws URISyntaxException{
        // 创建连接池
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        // 连接池配置
        // 设置最大连接数
        connectionManager.setMaxTotal(100);
        // 设置每个路由的最大连接数
        connectionManager.setDefaultMaxPerRoute(20);


        // 配置连接池中连接的参数
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(5000)// 发送请求的超时时间
                .setSocketTimeout(2000)// 从服务器获取响应数据的超时时间
                .setConnectionRequestTimeout(500)// 从连接池获取连接的超时时间
                .build();

        // 创建HttpClient
        CloseableHttpClient client = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(config)
                // 设置代理
                .setProxy(new HttpHost("121.41.54.26", 80))
                .build();

    }


    /**
     * 连接池配置
     * setMaxTotal(100): 设置最大连接数
     * setDefaultMaxPerRoute(20): 设置路由最大连接数
     *
     * 连接池中的连接配置
     * setConnectTimeout(1000): 设置连接超时时间
     * setSocketTimeout(1000): 设置等待数据的超时时间
     * setConnectionRequestTimeout(1000): 设置从连接池获取连接的超时时间
     * setProxy(): 设置代理
     */
    public static void poolParams() throws URISyntaxException {

        // 创建连接池
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        // 连接池配置
        // 设置最大连接数
        connectionManager.setMaxTotal(100);
        // 设置每个路由的最大连接数
        connectionManager.setDefaultMaxPerRoute(20);


        // 配置连接池中连接的参数
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(5000)// 发送请求的超时时间
                .setSocketTimeout(2000)// 从服务器获取响应数据的超时时间
                .setConnectionRequestTimeout(500)// 从连接池获取连接的超时时间
                .build();

        // 创建HttpClient
        CloseableHttpClient client = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(config)
                .build();

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
            // 这里不需要关闭client，因为连接池中的client可以资源重复利用
//            try {
//                client.close();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
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
