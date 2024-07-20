package com.oi.download;

import com.oi.process.TestPageProcessor;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.util.ArrayList;

/**
 * @author supanpan
 * @date 2024/07/20
 */
public class TestDownloader {
    public static void main(String[] args) {
        // 创建HttpClientDownloader
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        // 配置代理
        ArrayList<Proxy> proxies = new ArrayList<>();
        proxies.add(new Proxy("127.0.0.1", 8888));// 可以配置多个
        httpClientDownloader.setProxyProvider(new SimpleProxyProvider(proxies));

        Spider.create(new TestPageProcessor())
                .addUrl("http://www.baidu.com")
                .setDownloader(httpClientDownloader)
                .run();
    }
}
