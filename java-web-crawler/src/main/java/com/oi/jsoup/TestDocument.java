package com.oi.jsoup;

import com.oi.utils.HttpClientUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 生成Document的三种方法
 *
 * @author supanpan
 * @date 2024/07/20
 */
public class TestDocument {
    public static void main(String[] args) {
        // 1通过解析url来生产document
        Document document = null;
        try {
            document = Jsoup.parse(new URL("http://www.baidu.com"), 10000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 2通过解析字符串生成document
//        String html = HttpClientUtil.doGet("http://www.baidu.com");
//        Document document1 = Jsoup.parse(html);
        // System.out.println(document1);

        // 3通过解析文件生成document

        Document document2 = null;
        try {
            document2 = Jsoup.parse(new File("/Users/panpan/Desktop/随身云后端工程师技术栈 · Wiki · WTC _ WTC-arch _ arch-doc · GitLab.html"),"UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(document2);
    }
}
