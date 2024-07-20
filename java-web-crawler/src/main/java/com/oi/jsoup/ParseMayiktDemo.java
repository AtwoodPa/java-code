package com.oi.jsoup;

import com.oi.utils.HttpClientUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * http://www.mayikt.com/
 * @author supanpan
 * @date 2024/07/20
 */
public class ParseMayiktDemo {
    public static void main(String[] args) {
        String url = "http://www.sikiedu.com/course/explore/javaee?page=";
        for (int i = 1; i <= 3; i++) {
            String content = HttpClientUtil.doGet(url + i);

            // 解析成dom
            Document dom = Jsoup.parse(content);

            // 获取课程列表的元素
            Element courseList = dom.select(".course-list>div").get(0);

            // 获取课程集合
            Elements list = courseList.select(">div");
            for (Element course : list) {
                // 获取课程的名称和价格
                String courseName = course.select(".link-dark").get(0).text();
                String coursePrice = course.select(".course-price-widget>span").get(0).text();
                System.out.println("课程的名称:" + courseName + "----课程的价格:" + coursePrice);
            }
        }
    }
}
