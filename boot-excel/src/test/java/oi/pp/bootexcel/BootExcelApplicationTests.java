package oi.pp.bootexcel;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static oi.pp.bootexcel.SpiderUtil.dyReg1;
import static oi.pp.bootexcel.SpiderUtil.dyReg3;

@SpringBootTest
class BootExcelApplicationTests {
    public static String getLocationUrl(String urlString) {
        try {
            URL url = new URL(urlString.trim());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(50000);
            connection.setReadTimeout(50000);
            connection.setInstanceFollowRedirects(false); // 不允许自动重定向

            int responseCode = connection.getResponseCode();
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
    @Test
    void contextLoads() {
        String site = "";
//        String dyLinks = " 5- 长按复制此条消息，打A的更多作品。 https://v.douyin.com/i8TYJdBm/ 2@2.com :8pm";
        String dyLinks1 = "这个链接记得是往后端传，出去中文后的链接，就是这个https://v.douyin.com/iB7kLcM9/ 7@7.com :4pm";// 这个是对的https://www.iesdouyin.com/share/user/MS4wLjABAAAABUBk5Aqa53JPk8_fbbPyJHVF9YbgJSvE2b-rZ8QNmws
        String shortLink1 = SpiderUtil.getIdFromReg(dyLinks1, dyReg3, 0);
        if (StringUtils.isNotBlank(shortLink1)){
            System.out.println("提取到的短链接1：" + shortLink1);
            site = getLocationUrl(shortLink1);
            System.out.println(site);
        }
        System.out.println("---------------------------------------");
        String dyLinks2 = "这个链接记得是往后端传，出去中文后的链接，就是这个https://v.douyin.com/ejQXAWs/";// 这个手机访问失败，返回的是https://www.iesdouyin.com/share/user/94362219626，这个是不对的
        String shortLink2 = SpiderUtil.getIdFromReg(dyLinks2, dyReg3, 0);
        System.out.println("提取到的短链接2：" + shortLink2);
        assert shortLink2 != null;
        System.out.println(getLocationUrl(shortLink2));
    }

}
