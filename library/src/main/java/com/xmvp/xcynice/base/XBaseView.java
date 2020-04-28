package com.xmvp.xcynice.base;

/**
 * Description : XBaseView
 *
 * @author XuCanyou666
 * @date 2020/2/7
 */


@SuppressWarnings("ALL")
public interface XBaseView {

    void showLoading();

    void hideLoading();

    void onErrorCode(XBaseBean bean);

}
