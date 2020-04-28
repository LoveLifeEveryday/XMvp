package com.xmvp.xcynice;


/**
 * retrofit 配置
 *
 * @Author xucanyou666
 * @Date 2020/4/27 15:58
 * email：913710642@qq.com
 */
public class XBaseRetrofitConfig {
    private static String baseUrl;

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String baseApi) {
        XBaseRetrofitConfig.baseUrl = baseApi;
    }
}
