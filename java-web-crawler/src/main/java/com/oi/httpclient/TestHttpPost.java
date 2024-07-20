package com.oi.httpclient;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author supanpan
 * @date 2024/07/20
 */
public class TestHttpPost {
    public static void main(String[] args) {
        httpPost();
    }

    /**
     * 测试Post请求
     */
    private static void httpPost() {
        try {
            // 创建一个默认的httpClient实例
            CloseableHttpClient client = HttpClients.createDefault();
            // 构造请求参数
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("app", "ip.local"));
            nameValuePairs.add(new BasicNameValuePair("format", "json"));
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost("api.k780.com")
                    .setParameters(nameValuePairs)
                    .build();
            HttpPost httpPost = new HttpPost(uri);
            // 发出响应
            CloseableHttpResponse response = client.execute(httpPost);
            // 获取请求响应码
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 == statusCode){
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                String json = EntityUtils.toString(entity, "UTF-8");
                // 解析json拿到具体数据
                /**
                 * {"success":1,
                 *  "result":{
                 *          "ip":"123.57.233.17",
                 *          "country":"中国",
                 *          "province":"广东",
                 *          "city":"广州",
                 *          "district":"天河",
                 *          "carrier":"电信"
                 *          }
                 * }
                 */
                Map<String, Object> map = (Map<String, Object>) JSONObject.parse(json);
                Map<String, Object>  result = (Map<String, Object>) map.get("result");
                // 获取ip
                String ip = (String) result.get("ip");
                System.out.println("ip = " + ip);
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
