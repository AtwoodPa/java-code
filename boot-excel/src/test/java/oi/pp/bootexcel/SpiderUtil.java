package oi.pp.bootexcel;

import org.junit.platform.commons.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpiderUtil {
    public static final Pattern xhsReg3 = Pattern.compile("\\.xiaohongshu\\.com/user/profile/([0-9a-zA-Z\\s]*)\\??");
    public static final Pattern dyReg1 = Pattern.compile("\\.iesdouyin\\.com/share/user/([\\_\\-0-9a-zA-Z\\s]*)\\??");

    // https://www.douyin.com/user/MS4wLjABAAAA_yNh0oPlygtubdb0GPxOKluWwzLrkqXko4IJ_Yrpbtw?forGaoqu=1&fromTabId=0
    public static final Pattern dyReg2 = Pattern.compile("\\.douyin\\.com/user/([\\_\\-0-9a-zA-Z\\s]*)\\??");

    // 5- 长按复制此条消息，打开抖音搜索，查看TA的更多作品。 https://v.douyin.com/i8TYJdBm/ 2@2.com :8pm
    public static final Pattern dyReg3 = Pattern.compile("https://v\\.douyin\\.com/([\\_\\-0-9a-zA-Z]*)/?");
//    public static final Pattern dyReg3 = Pattern.compile("https://v\\.douyin\\.com/([\\_\\-0-9a-zA-Z\\s]*)/?");

    /**
     * 小红书
     * @param link
     * @param pattern
     * @param group
     * @return
     */
    public static String getIdFromReg(String link, Pattern pattern, int group) {
        if (StringUtils.isNotBlank(link)) {
            Matcher matcher1 = pattern.matcher(link);
            if (matcher1.find()) {
                return matcher1.group(group).replaceAll("\\s", "");
            }
        }
        return null;
    }

    public static String getIdFromReg(String link, Pattern pattern) {
        return getIdFromReg(link, pattern, 1);
    }
    public static String getBloggerIdFromXhsLink(String site) {
        return getIdFromReg(site, xhsReg3);
    }

    /**
     * 抖音
     * @param site
     * @return
     */
//    public static String getBloggerIdFromDyLink(String site) {
//        // https://v.douyin.com/i8j268pv
//        String shortLink = getIdFromReg(site, dyReg3, 0);
//        if (StringUtils.isNotBlank(shortLink)) {
//            // https://www.iesdouyin.com/share/user/MS4wLjABAAAA5rCv0gcZ3TJnZe_zo66YYKtVSDVg9bWUX6yr5iveNkk?u_code=...
//            site = AjaxUtil.getLocationUrl(shortLink);
//        }
//        String id = getIdFromReg(site, dyReg1);
//        if (StringUtils.isBlank(id)) {
//            // https://www.douyin.com/user/MS4wLjABAAAA_yNh0oPlygtubdb0GPxOKluWwzLrkqXko4IJ_Yrpbtw?forGaoqu=1&fromTabId=0
//            id = getIdFromReg(site, dyReg2);
//        }
//        return id;
//    }

    /**
     * 跳转抖音全链接
     * @param bloggerId
     * @return
     */
    public static String getDyLinkFromBloggerId(String bloggerId) {
        if (StringUtils.isNotBlank(bloggerId)) {
            return "https://www.douyin.com/user/" + bloggerId;
        }
        return null;
    }

}
