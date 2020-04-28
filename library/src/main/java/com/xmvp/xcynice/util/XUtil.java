package com.xmvp.xcynice.util;


import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;


/**
 * @Author xucanyou666
 * @Date 2020/4/27 09:26
 * email：913710642@qq.com
 */
public class XUtil {
    private static Application mApplicationContext;
    private static ProgressDialog progressDialog;

    public static void initialize(Application app) {
        mApplicationContext = app;
    }

    public static Application getApplication() {
        return mApplicationContext;
    }


    /**
     * 在当前 activity 显示加载中
     * @param msg 信息
     */
    public static void showLoading(String msg) {
        progressDialog = ProgressDialog.show(ActivityUtil.getCurrentActivity(), "", msg, true, true);
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();
    }

    /**
     * 在指定 activity 显示加载中
     * @param activity activity
     * @param msg 信息
     */
    public static void showLoading(Activity activity, String msg) {
        progressDialog = ProgressDialog.show(activity, "", msg, true, true);
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();
    }

    public static void dismissLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public static void closeSoftKeyboard() {
        InputMethodManager inputManger = (InputMethodManager) ActivityUtil.getCurrentActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManger != null) {
            inputManger.hideSoftInputFromWindow(ActivityUtil.getCurrentActivity().getWindow().getDecorView().getWindowToken(), 0);
        }
    }

}
