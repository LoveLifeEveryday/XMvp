package com.xcynice.XMvp.module.home;


import com.xcynice.XMvp.bean.Article;
import com.xmvp.xcynice.base.XBaseBean;
import com.xmvp.xcynice.base.XBaseView;

/**
 * created by xucanyou666
 * on 2020/2/8 00:37
 * email：913710642@qq.com
 */
public interface IHomeView extends XBaseView {
    /**
     * 设置文章数据
     *
     * @param list 文章list
     */
    void setArticleData(XBaseBean<Article> list);

    /**
     * 显示文章失败
     *
     * @param errorMessage 失败信息
     */
    void showArticleError(String errorMessage);

    /**
     * 显示收藏成功
     *
     * @param successMessage 成功信息
     */
    void showCollectSuccess(String successMessage);

    /**
     * 显示收藏失败
     *
     * @param errorMessage 失败信息
     */
    void showCollectError(String errorMessage);

    /**
     * 显示未收藏成功
     *
     * @param successMessage 成功信息
     */
    void showUncollectSuccess(String successMessage);

    /**
     * 显示未收藏失败
     *
     * @param errorMessage 失败信息
     */
    void showUncollectError(String errorMessage);
}
