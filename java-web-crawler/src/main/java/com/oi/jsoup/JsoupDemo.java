package com.oi.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author supanpan
 * @date 2024/07/20
 */
public class JsoupDemo {
    public static void main(String[] args) {
        // 创建一个HTML文档
        String html = "<html><head><title>Example</title></head>" +
                "<body><p>Example paragraph</p></body></html>";
        // 使用Jsoup解析HTML文档
        Document doc = Jsoup.parse(html);
        System.out.println(doc);
        // 输出文档的标题
        System.out.println("Title: " + doc.title());
        // 输出文档中的段落内容
        System.out.println("Paragraph: " + doc.select("p").text());
    }
}
