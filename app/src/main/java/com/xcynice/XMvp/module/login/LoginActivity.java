package com.xcynice.XMvp.module.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.xcynice.XMvp.R;
import com.xcynice.XMvp.module.home.MainActivity;
import com.xcynice.XMvp.module.register.RegisterActivity;
import com.xmvp.xcynice.base.XBaseActivity;
import com.xmvp.xcynice.util.ActivityUtil;
import com.xmvp.xcynice.util.ToastUtil;
import com.xmvp.xcynice.util.XUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description : LoginActivity
 *
 * @author XuCanyou666
 * @date 2020/2/8
 */


public class LoginActivity extends XBaseActivity<LoginPresenter> implements ILoginView {

    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.til_username)
    TextInputLayout mTilUsername;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.til_password)
    TextInputLayout mTilPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.btn_register)
    Button mBtnRegister;


    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        LoginTextWatcher textWatcher = new LoginTextWatcher(mTilUsername, mTilPassword);
        mEtUsername.addTextChangedListener(textWatcher);
        mEtPassword.addTextChangedListener(textWatcher);
    }


    @Override
    public void showLoginSuccess(String successMessage) {
        ToastUtil.showToast(successMessage);
    }

    @Override
    public void showLoginFailed(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    @Override
    public void doSuccess() {
        ActivityUtil.startActivity(MainActivity.class, true);
    }


    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String username = mEtUsername.getText().toString().trim();
                String password = mEtPassword.getText().toString().trim();
                int tilUsernameCounterMaxLength = mTilUsername.getCounterMaxLength();
                int tilPasswordCounterMaxLength = mTilPassword.getCounterMaxLength();
                presenter.login(username, password, tilUsernameCounterMaxLength, tilPasswordCounterMaxLength);
                break;
            case R.id.btn_register:
                XUtil.closeSoftKeyboard();
                ActivityUtil.startActivity(RegisterActivity.class);
                break;
            default:
                break;
        }
    }
}
