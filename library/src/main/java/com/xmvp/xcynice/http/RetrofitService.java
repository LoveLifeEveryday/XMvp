package com.xmvp.xcynice.http;

import com.xmvp.xcynice.XBaseRetrofitConfig;
import com.xmvp.xcynice.http.cookie.CookiesManager;
import com.xmvp.xcynice.http.gson.BaseConverterFactory;
import com.xmvp.xcynice.util.XUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Description : RetrofitService
 *
 * @author XuCanyou666
 * @date 2020/2/8
 */


public class RetrofitService {

    private volatile static RetrofitService apiRetrofit;
    private Retrofit mRetrofit;


    /**
     * 单例调用
     *
     * @return RetrofitService
     */
    public static RetrofitService getInstance() {
        if (apiRetrofit == null) {
            synchronized (Object.class) {
                if (apiRetrofit == null) {
                    apiRetrofit = new RetrofitService();
                }
            }
        }
        return apiRetrofit;
    }


    /**
     * 初始化retrofit
     */
    private RetrofitService() {
        //配置okHttp并设置时间、日志信息和cookies
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                //设置超时时间
                .connectTimeout(15, TimeUnit.SECONDS)
                //设置Cookie持久化
                .cookieJar(new CookiesManager(XUtil.getApplication()))
                .build();

        //关联okHttp并加上rxJava和Gson的配置和baseUrl
        mRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(BaseConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(XBaseRetrofitConfig.getBaseUrl())
                .build();
    }

    /**
     * 利用泛型传入接口class返回接口实例
     *
     * @param ser 类
     * @param <T> 类的类型
     * @return Observable
     */
    public <T> T createRs(Class<T> ser) {
        return mRetrofit.create(ser);
    }

}
