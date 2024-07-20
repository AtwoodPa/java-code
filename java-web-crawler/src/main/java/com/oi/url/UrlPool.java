package com.oi.url;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author supanpan
 * @date 2024/07/19
 */
public class UrlPool {
    public static void main(String[] args) {
        getUrl("https://www.nipic.com/");
    }

    private static void getUrl(String baseUrl) {
        // 访问过的url
        Map<String, Boolean> oldMap = new LinkedHashMap<>();
        // 处理host
        String oldLinkHost = "";
        Pattern pattern = Pattern.compile("(https?://)?[^/\\s]*");
        Matcher m = pattern.matcher(baseUrl);
        if (m.find()) {
            oldLinkHost = m.group();
        }
        oldMap.put(baseUrl, false);
        // 遍历操作
        oldMap = crawlLinks(oldLinkHost, oldMap);
        // 打印map
        printMap(oldMap);
    }

    /**
     * 处理链接
     *
     * @param oldLinkHost
     * @param oldMap
     */
    private static Map<String, Boolean> crawlLinks(String oldLinkHost, Map<String, Boolean> oldMap) {
        Map<String, Boolean> newMap = new LinkedHashMap<>();
        // 根据oldLink，发送http请求
        String oldLink = "";

        for (Map.Entry<String, Boolean> mapping : oldMap.entrySet()) {
            if (!mapping.getValue()){
                oldLink = mapping.getKey();
                // 处理链接
                try {
                    URL url = new URL(oldLink);
                    // 利用HttpURLConnection创建链接
                    HttpURLConnection connection  = (HttpURLConnection) url.openConnection();
                    // 设置请求类型 => GET
                    connection.setRequestMethod("GET");
                    // 获取成功
                    if (connection.getResponseCode() == 200){
                        // 读取流数据 => 用缓冲流进行读取
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(connection.getInputStream()));
                        Pattern p = Pattern.compile("<a[^>]*href=[\"']?([^\"'>]+)[\"']?[^>]*>");
                        Matcher matcher = null;
                        String line = "";
                        while ((line = reader.readLine()) != null){
                            matcher = p.matcher(line);
                            while (matcher.find()){// 查看是否匹配成功
                                String newLink = matcher.group(1);
                                if (newLink.startsWith("http")){
                                   if (newLink.startsWith("/")){
                                       newLink = oldLinkHost + newLink;
                                   }else {
                                       newLink = oldLinkHost + "/" + newLink;
                                   }
                                }
                                if (newLink.endsWith("/")){
                                    newLink = newLink.substring(0, newLink.length() - 1);
                                }
                                // 判断链接是否重复，并且只匹配指定站点数据
                                if (!oldMap.containsKey(newLink) &&
                                        !newMap.containsKey(newLink) &&
                                            newLink.startsWith(oldLinkHost)){
                                    newMap.put(newLink, false);
                                }

                            }
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {

                }
                oldMap.replace(oldLink, false, true);
            }
        }
        if (!newMap.isEmpty()){
            oldMap.putAll(newMap);
            // 继续递归
            oldMap.putAll(crawlLinks(oldLinkHost, oldMap));
        }
        return oldMap;

    }

    /**
     * 遍历map操作
     *
     * @param oldMap
     */
    private static void printMap(Map<String, Boolean> oldMap) {
        for (Map.Entry<String, Boolean> entry : oldMap.entrySet()) {
            System.out.println("链接：" + entry.getKey());
        }
    }
}
