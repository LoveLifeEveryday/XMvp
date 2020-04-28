package com.xcynice.XMvp;


import android.app.Application;

import com.xcynice.XMvp.api.TestApi;
import com.xcynice.XMvp.contants.Contant;
import com.xmvp.xcynice.app.XMvp;

/**
 * @Author xucanyou666
 * @Date 2020/4/27 15:26
 * email：913710642@qq.com
 */
public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化XMvp
        XMvp.init(this, Contant.BASE_URL);
    }
}
