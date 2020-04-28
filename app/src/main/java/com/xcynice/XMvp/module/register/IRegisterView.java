package com.xcynice.XMvp.module.register;

import com.xcynice.XMvp.bean.User;
import com.xmvp.xcynice.base.XBaseBean;
import com.xmvp.xcynice.base.XBaseView;

/**
 * Description : IRegisterView
 *
 * @author XuCanyou666
 * @date 2020/2/8
 */

public interface IRegisterView extends XBaseView {

    /**
     * 显示注册成功
     *
     * @param successMessage
     */
    void showRegisterSuccess(String successMessage);

    /**
     * 显示注册失败
     *
     * @param errorMessage
     */
    void showRegisterFailed(String errorMessage);

    /**
     * 注册成功
     *
     * @param user
     */
    void doSuccess(XBaseBean<User> user);

}
