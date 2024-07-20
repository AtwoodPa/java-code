package com.oi.process;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author supanpan
 * @date 2024/07/20
 */
public class MyProcess implements PageProcessor {
    @Override
    public void process(Page page) {
        Document document = page.getHtml().getDocument();
        Element span = document.select(".subtitle").get(0).previousElementSibling().select("span").get(0);
        String text = span.text();
        page.putField("title", text);
    }

    @Override
    public Site getSite() {
        return new Site();
    }

    public static void main(String[] args) {

    }
}
