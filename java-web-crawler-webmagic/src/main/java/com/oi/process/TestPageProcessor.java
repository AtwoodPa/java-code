package com.oi.process;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

/**
 * @author supanpan
 * @date 2024/07/20
 */
public class TestPageProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        System.out.println(html);
        // 用xpath获取ip
//        List<String> ipList = html.xpath("//table[@class='table table-bordered table-striped']//tbody//tr//td[1]//text()").all();
//        for (String s : ipList) {
//           // System.out.println(s);
//        }
        // 用css选择器获取端口号
//        List<String> portList = html.css("#table tbody tr td:nth-child(2)", "text").all();
//
//        for (String s : portList) {
//            System.out.println(s);
//        }
        // 通过css选择器获取服务器地址
//        List<String> links = html.css("#ip_list tbody tr td:nth-child(2)")
//                .links().regex(".*/2018.*").all();
//        page.addTargetRequest(links.toString());


    }

    // 设置网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me()
            .setCharset("utf-8")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36")
            .setTimeOut(10000).setRetryTimes(3).setSleepTime(1000)
            .addHeader("cookie","BIDUPSID=A7013975549943C397DF2B2FC906181D; PSTM=1698298289; BAIDUID=A7013975549943C374F751AA9DCAE919:FG=1; BDUSS=nhhalF3RWFtNkRnMUs2WXYtTEdqV3BrN25-NDN1Y1BjVUI2VXEybmM1a0tnfjFsSVFBQUFBJCQAAAAAAAAAAAEAAABd7-2Lvv2yu8~bz8lsaWZlAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAr21WUK9tVlN; BDUSS_BFESS=nhhalF3RWFtNkRnMUs2WXYtTEdqV3BrN25-NDN1Y1BjVUI2VXEybmM1a0tnfjFsSVFBQUFBJCQAAAAAAAAAAAEAAABd7-2Lvv2yu8~bz8lsaWZlAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAr21WUK9tVlN; MCITY=-277%3A; BD_UPN=123253; ispeed_lsm=2; H_WISE_SIDS_BFESS=60277_60363; BDSFRCVID=QntOJexroG3M8X5tudgWMozKwSSF1OoTDYLEdocWi2w5BDuVYEc0EG0PtOqcCoC-KA06ogKK0eOTHk-F_2uxOjjg8UtVJeC6EG0Ptf8g0M5; H_BDCLCKID_SF=JRPJ_C82JKP3DPJNhCTDMt-DhUO-2I62aJ34oCnvWJ5TMC_wQtb8Wl_3jPCeaxryMe3fbKbbLM0bShPC-tn2bRjLeqbftJTkQgbAQKnq3l02V-cEe-t2yT0J5Mb3W-RMW20j0l7mWnkVsxA45J7cM4IseboJLfT-0bc4KKJxbnLWeIJ9jjCKDTOBja-et5nfb5kXXJnV-nT_KROvhjR_LUFgyxoObtRxt2jybRo2J-JNSl6v2xRbbfPUDMJ9LU3kBgTmQfcMyJcJMpv1QljZyRKpQttjQn3BWJT7-CbtBPoNbJ7TyU42bf47yM4L0q4Hb6b9BJcjfU5MSlcNLTjpQT8r5MDOK5OuJRLeVC-afCIahIvpK-rthnvH-UnLqhcDfgOZ04n-ah05sJnmMROAhUuIXfnEBbb-W23hbUom3UTKsquRbUbn2hDWX4DOLf5C5Ij4KKJxbp-hstbELfcGbpDAhUJiB5O-Ban7B56IXKohJh7FM4tW3J0ZyxomtfQxtNRJ0DnjtpChbC-xe5uMDjJ-eU5y5RjbaC5OsJO8f-OzVh7_bf--Dx_qjxTh2b3t5mn32R7LW-3qJbQo0fnxy5K_hp5U363u0m3Ohqb9QbKMEj6HQT3mLRvbbN3i-xribRKLWb3cWKJq8UbSMIcPBTD02-nBat-OQ6npaJ5nJq5nhMJmb67JD-50eGteJj_eJbPsL-35HtTED4nY-tTJq4tehHRI2J39WDTOQJ7TthCWoK5m5tOJ3Uue24RfQhJltmOy-pbwBp5fDnO5Mj5bjfDv5Jjk04tj3mkjbPbv-ROTDp8zyUL2j44syP4jKxRnWI0LKfA-b4ncjRcTehoM3xI8LNj405OTbIFO0KJzJCcjqR8ZjjAWe53P; H_PS_PSSID=60277_60441_60491_60501; H_WISE_SIDS=60277_60441_60491_60501; BAIDUID_BFESS=A7013975549943C374F751AA9DCAE919:FG=1; BDSFRCVID_BFESS=QntOJexroG3M8X5tudgWMozKwSSF1OoTDYLEdocWi2w5BDuVYEc0EG0PtOqcCoC-KA06ogKK0eOTHk-F_2uxOjjg8UtVJeC6EG0Ptf8g0M5; H_BDCLCKID_SF_BFESS=JRPJ_C82JKP3DPJNhCTDMt-DhUO-2I62aJ34oCnvWJ5TMC_wQtb8Wl_3jPCeaxryMe3fbKbbLM0bShPC-tn2bRjLeqbftJTkQgbAQKnq3l02V-cEe-t2yT0J5Mb3W-RMW20j0l7mWnkVsxA45J7cM4IseboJLfT-0bc4KKJxbnLWeIJ9jjCKDTOBja-et5nfb5kXXJnV-nT_KROvhjR_LUFgyxoObtRxt2jybRo2J-JNSl6v2xRbbfPUDMJ9LU3kBgTmQfcMyJcJMpv1QljZyRKpQttjQn3BWJT7-CbtBPoNbJ7TyU42bf47yM4L0q4Hb6b9BJcjfU5MSlcNLTjpQT8r5MDOK5OuJRLeVC-afCIahIvpK-rthnvH-UnLqhcDfgOZ04n-ah05sJnmMROAhUuIXfnEBbb-W23hbUom3UTKsquRbUbn2hDWX4DOLf5C5Ij4KKJxbp-hstbELfcGbpDAhUJiB5O-Ban7B56IXKohJh7FM4tW3J0ZyxomtfQxtNRJ0DnjtpChbC-xe5uMDjJ-eU5y5RjbaC5OsJO8f-OzVh7_bf--Dx_qjxTh2b3t5mn32R7LW-3qJbQo0fnxy5K_hp5U363u0m3Ohqb9QbKMEj6HQT3mLRvbbN3i-xribRKLWb3cWKJq8UbSMIcPBTD02-nBat-OQ6npaJ5nJq5nhMJmb67JD-50eGteJj_eJbPsL-35HtTED4nY-tTJq4tehHRI2J39WDTOQJ7TthCWoK5m5tOJ3Uue24RfQhJltmOy-pbwBp5fDnO5Mj5bjfDv5Jjk04tj3mkjbPbv-ROTDp8zyUL2j44syP4jKxRnWI0LKfA-b4ncjRcTehoM3xI8LNj405OTbIFO0KJzJCcjqR8ZjjAWe53P; BD_CK_SAM=1; PSINO=5; delPer=0; BA_HECTOR=a00h248k2g81252ka4a5858h032a5l1j9ngqt1v; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; ZFY=uB3CJhUVNODCFeknfon0w3Dhapz1CD1g7:ASgFWDWfE4:C; H_PS_645EC=a076ZhTKlVhfEEZFBK6VwTARh66XiAqXZKh0wYQJfbLfjbHEXRshTsdjolI; BDSVRTM=173; WWW_ST=1721484260851");

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new TestPageProcessor())
                .addUrl("https://www.baidu.com/s?wd=Webmagic")
                .run();
    }
}
