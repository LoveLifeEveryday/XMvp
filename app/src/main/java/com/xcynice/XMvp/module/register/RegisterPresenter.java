package com.xcynice.XMvp.module.register;

import android.text.TextUtils;

import com.xcynice.XMvp.api.TestApi;
import com.xcynice.XMvp.bean.User;
import com.xmvp.xcynice.base.XBaseBean;
import com.xmvp.xcynice.base.XBaseObserver;
import com.xmvp.xcynice.base.XBasePresenter;
import com.xmvp.xcynice.util.XUtil;

/**
 * Description : RegisterPresenter
 *
 * @author XuCanyou666
 * @date 2020/2/8
 */


class RegisterPresenter extends XBasePresenter<IRegisterView> {

    RegisterPresenter(IRegisterView baseView) {
        super(baseView);
    }

    void register(String username, String password, String rePassword, int usernameCountMax, int passwordCountMax, int rePasswordCountMax) {
        XUtil.closeSoftKeyboard();
        //判断输入是否合规
        if (checkIsValid(username, password, rePassword, usernameCountMax, passwordCountMax, rePasswordCountMax)) {
            //判断两次密码输入是否一致
            if (password.equals(rePassword)) {
                addDisposable(retrofitService.createRs(TestApi.class).register(username, password, rePassword), new XBaseObserver<XBaseBean<User>>(baseView, true) {
                    @Override
                    public void onSuccess(XBaseBean<User> bean) {
                        baseView.showRegisterSuccess("注册成功（￣▽￣）");
                        baseView.doSuccess(bean);
                    }

                    @Override
                    public void onError(String msg) {
                        baseView.showRegisterFailed(msg + "(°∀°)ﾉ");
                    }
                });
            } else {
                baseView.showRegisterFailed("两次密码不一样( ⊙ o ⊙ ) ");
            }
        } else {
            baseView.showRegisterFailed("填写错误 (°∀°)ﾉ");
        }
    }

    /**
     * 整体判断输入的内容是否合规
     *
     * @param username           账号
     * @param password           密码
     * @param rePassword         再次输入的密码
     * @param usernameCountMax   账号最大输入字数
     * @param passwordCountMax   密码最大输入字数
     * @param rePasswordCountMax 再次输入密码最大输入字数
     * @return 是否合规
     */
    private boolean checkIsValid(String username, String password, String rePassword, int usernameCountMax, int passwordCountMax, int rePasswordCountMax) {
        return isUsernameValid(username, usernameCountMax) && isPasswordValid(password, passwordCountMax) && isRePasswordValid(rePassword, rePasswordCountMax);
    }

    /**
     * 判断username输入是否合规
     *
     * @param username         username
     * @param usernameCountMax 账号规定输入字符最大值
     * @return 是否合规
     */
    private boolean isUsernameValid(String username, int usernameCountMax) {
        return !TextUtils.isEmpty(username) && username.length() <= usernameCountMax && username.length() >= usernameCountMax / 2;
    }

    /**
     * 判断password输入是否合规
     *
     * @param password         password
     * @param passwordCountMax 密码规定输入字符最大值
     * @return 是否合规
     */
    private boolean isPasswordValid(String password, int passwordCountMax) {
        return !TextUtils.isEmpty(password) && password.length() <= passwordCountMax && password.length() >= passwordCountMax / 2;
    }

    /**
     * 判断rePassword输入是否合规
     *
     * @param rePassword         确认密码
     * @param rePasswordCountMax 确认密码的规定输入字符最大值
     * @return 是否合规
     */
    private boolean isRePasswordValid(String rePassword, int rePasswordCountMax) {
        return !TextUtils.isEmpty(rePassword) && rePassword.length() <= rePasswordCountMax && rePassword.length() >= rePasswordCountMax / 2;
    }
}
