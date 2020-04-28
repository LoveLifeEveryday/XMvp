package com.xcynice.XMvp;


import com.xcynice.XMvp.contants.Contant;
import com.xmvp.xcynice.app.XMvp;

/**
 * @Author xucanyou666
 * @Date 2020/4/27 15:26
 * email：913710642@qq.com
 */
public class BaseApplication extends XMvp {

    @Override
    public String initBaseUrl() {
        return Contant.BASE_URL;
    }
}
