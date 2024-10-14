package oi.pp.bootexcel;

public class AjaxUtil {
    public static String getLocationUrl(String url) {
        return url;
//        RequestConfig config = RequestConfig.custom().setConnectTimeout(50000).setConnectionRequestTimeout(10000).setSocketTimeout(50000)
//                .setRedirectsEnabled(false).build();//不允许重定向
//        HttpGet httpGet = new HttpGet(url.trim());
//        httpGet.setConfig(config);
//        try (CloseableHttpResponse response = createSSLClientFromConnPool().execute(httpGet)) {
//            int responseCode = response.getStatusLine().getStatusCode();
//            if (responseCode == 302 || responseCode == 307) {
//                Header locationHeader = response.getFirstHeader("Location");
//                return locationHeader.getValue();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
    }
}
