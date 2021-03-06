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
public abstract class XMvp extends Application {
    protected String mBaseUrl;


    public abstract String initBaseUrl();


    @Override
    public void onCreate() {
        super.onCreate();
        mBaseUrl = initBaseUrl();
        init();
    }

    /**
     * 初始化 application
     */
    public void init() {
        //初始化
        XUtil.initialize(this);
        //设置打印开关
        LogUtil.setIsLog(true);
        //注册Activity生命周期
        registerActivityLifecycleCallbacks(ActivityUtil.getActivityLifecycleCallbacks());
        XBaseRetrofitConfig.setBaseUrl(mBaseUrl);
    }
}
