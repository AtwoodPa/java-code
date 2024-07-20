package com.oi.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * @author supanpan
 * @date 2024/07/20
 */
public class TestGetElement {
    public static void main(String[] args) throws Exception{
        Document document = Jsoup.parse(new File("/Users/panpan/Desktop/随身云后端工程师技术栈 · Wiki · WTC _ WTC-arch _ arch-doc · GitLab.html"),"UTF-8");

        Element elementById = document.getElementById("js-onboarding-new-project-link");
        Elements lis = document.getElementsByTag("li");
        for (Element li : lis){
            // System.out.println(li.text());
        }
        Elements elementsByClass = document.getElementsByClass("no-touch");

        System.out.println(elementsByClass);

        document.getElementsByAttribute("data-qa-selector");

        document.getElementsByAttributeValue("data-qa-selector","onboarding-new-project-link");
    }
}
