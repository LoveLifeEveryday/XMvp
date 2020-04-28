package com.xmvp.xcynice.app;


import android.app.Application;

import com.xmvp.xcynice.XBaseRetrofitConfig;
import com.xmvp.xcynice.util.ActivityUtil;
import com.xmvp.xcynice.util.LogUtil;
import com.xmvp.xcynice.util.XUtil;

/**
 * @Author xucanyou666
 * @Date 2020/4/27 15:08
 * email：913710642@qq.com
 */
public class XMvp {


    /**
     * 初始化 application
     *
     * @param application application
     * @param baseUrl     baseUrl
     */
    public static void init(Application application, String baseUrl) {
        //初始化
        XUtil.initialize(application);
        //设置打印开关
        LogUtil.setIsLog(true);
        //注册Activity生命周期
        application.registerActivityLifecycleCallbacks(ActivityUtil.getActivityLifecycleCallbacks());
        XBaseRetrofitConfig.setBaseUrl(baseUrl);
    }
}
