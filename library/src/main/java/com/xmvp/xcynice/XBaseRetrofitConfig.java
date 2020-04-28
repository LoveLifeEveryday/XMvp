package com.xmvp.xcynice;


/**
 * retrofit 配置
 *
 * @Author xucanyou666
 * @Date 2020/4/27 15:58
 * email：913710642@qq.com
 */
public class XBaseRetrofitConfig {
    public static String baseUrl;
    public static Class xBaseApi;


    public static String getBaseUrl() {
        return baseUrl;
    }


    public static Class getxBaseApi() {
        return xBaseApi;
    }

    public static void setxBaseApi(Class xBaseApi) {
        XBaseRetrofitConfig.xBaseApi = xBaseApi;
    }

    public static void setxBaseUrl(String xBaseApi) {
        XBaseRetrofitConfig.baseUrl = xBaseApi;
    }
}
