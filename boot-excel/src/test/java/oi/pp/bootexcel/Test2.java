package oi.pp.bootexcel;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static oi.pp.bootexcel.SpiderUtil.getIdFromReg;

public class Test2 {
    // 正则表达式，匹配抖音短链接
    public static final Pattern dyReg3 = Pattern.compile("https://v\\.douyin\\.com/([\\_\\-0-9a-zA-Z]{7,8})/?");
    // 提取 sec_uid 的正则表达式
    private static final Pattern SEC_UID_PATTERN = Pattern.compile("sec_uid=([a-zA-Z0-9_\\-]+)");
    // 获取重定向URL的方法
    public static String getLocationUrl(String urlString) {
        try {
            URL url = new URL(urlString.trim());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(50000);
            connection.setReadTimeout(50000);
            connection.setInstanceFollowRedirects(false); // 不允许自动重定向

            int responseCode = connection.getResponseCode();
            Map<String, List<String>> headerFields = connection.getHeaderFields();
            // 遍历map
            for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
                String key = entry.getKey();
                List<String> values = entry.getValue();
                for (String value : values) {
                    System.out.println(key + ": " + value);
                }
            }
//            System.out.println("connection ==> " + headerFields);
            if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP ||
                    responseCode == HttpURLConnection.HTTP_MOVED_PERM ||
                    responseCode == 307) {
                // 获取重定向的URL
                return connection.getHeaderField("Location");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 发起直接请求获取返回内容
    public static String fetchContent(String urlString) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(urlString.trim());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(50000);
            connection.setReadTimeout(50000);
            connection.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    @Test
    void contextLoads() {
        // 测试的短链接字符串
        String dyLinks1 = "这个链接记得是往后端传，出去中文后的链接，就是这个https://v.douyin.com/iB7kLcM9/ 7@7.com :4pm";
        String dyLinks2 = "这个链接记得是往后端传，出去中文后的链接，就是这个https://v.douyin.com/ejQXAWs/";

        // 提取短链接
        String shortLink1 = getShortLink(dyLinks1, dyReg3);
        String shortLink2 = getShortLink(dyLinks2, dyReg3);

        // 分别处理两个短链接
        processShortLink(shortLink1);
        processShortLink(shortLink2);
    }
    private static String executePattern(String site, Pattern pattern) {
        Matcher matcher = pattern.matcher(site);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
    public static String buildLocationUrlFromSecUid(String siteUrl) {
        Matcher matcher = SEC_UID_PATTERN.matcher(siteUrl);
        if (matcher.find()) {
            String secUid = matcher.group(1);
            return "https://www.iesdouyin.com/share/user/" + secUid;
        }
        return null;
    }
    // 处理短链接，根据长度决定是否直接访问或重定向
    private static final Pattern USER_ID_PATTERN = Pattern.compile("user/([a-zA-Z0-9]+)");

    public static void processShortLink(String shortLink) {
        if (shortLink != null) {
            String site = getLocationUrl(shortLink);
            String userAfter = getIdFromReg(site, USER_ID_PATTERN);

            if (userAfter != null && userAfter.matches("\\d+")) {
                site = buildLocationUrlFromSecUid(site);
                System.out.println("NEW URL   ====>" + site);
            }else {
                System.out.println("OLD URL   ====>" + site);
            }
        }
    }

    // 根据正则表达式提取短链接
    public static String getShortLink(String text, Pattern pattern) {
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(0); // 获取匹配到的整个短链接
        }
        return null;
    }
}
