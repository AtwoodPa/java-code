package com.oi;

import com.oi.pipeline.MyPipeline;
import com.oi.process.MyProcess;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

/**
 * @author supanpan
 * @date 2024/07/20
 */
public class Main {
    static {
        // 设置系统属性
        System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1");
    }

    public static void main(String[] args) {
        demo1();
    }

    public static void demo1(){

        System.out.println("Hello WebMagic!");
        HttpClientDownloader downloader = new HttpClientDownloader();
        Spider spider = Spider.create(new MyProcess())
                .addPipeline(new MyPipeline())
                .setDownloader(downloader)
                .addUrl("https://www.sikiedu.com/");
        spider.run();
    }
}