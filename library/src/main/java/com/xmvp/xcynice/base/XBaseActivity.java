package com.xmvp.xcynice.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xmvp.xcynice.util.XUtil;

import butterknife.ButterKnife;

/**
 * Description : XBaseActivity
 *
 * @author XuCanyou666
 * @date 2020/2/7
 */

public abstract class XBaseActivity<P extends XBasePresenter> extends AppCompatActivity implements XBaseView {

    protected P presenter;

    protected abstract P createPresenter();

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(LayoutInflater.from(this).inflate(getLayoutId(), null));
        ButterKnife.bind(this);
        presenter = createPresenter();
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁时，解除绑定
        if (presenter != null) {
            presenter.detachView();
        }
    }

    protected void initListener() {
    }

    @Override
    public void showLoading() {
        XUtil.showLoading(this, "加载中");
    }

    @Override
    public void hideLoading() {
        XUtil.dismissLoading();
    }

    /**
     * 可以处理异常
     */
    @Override
    public void onErrorCode(XBaseBean bean) {
    }


}
