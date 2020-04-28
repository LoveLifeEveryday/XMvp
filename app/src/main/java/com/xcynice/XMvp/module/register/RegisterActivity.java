package com.xcynice.XMvp.module.register;

import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.xcynice.XMvp.R;
import com.xcynice.XMvp.bean.User;
import com.xmvp.xcynice.base.XBaseActivity;
import com.xmvp.xcynice.base.XBaseBean;
import com.xmvp.xcynice.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description : RegisterActivity
 *
 * @author XuCanyou666
 * @date 2020/2/8
 */


public class RegisterActivity extends XBaseActivity<RegisterPresenter> implements IRegisterView {

    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.til_username)
    TextInputLayout mTilUsername;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.til_password)
    TextInputLayout mTilPassword;
    @BindView(R.id.et_repassword)
    EditText mEtRepassword;
    @BindView(R.id.til_repassword)
    TextInputLayout mTilRepassword;
    @BindView(R.id.btn_register)
    Button mBtnRegister;

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        TextWatcher textWatcher = new RegisterTextWatcher(mTilUsername, mTilPassword, mTilRepassword);
        mEtUsername.addTextChangedListener(textWatcher);
        mEtPassword.addTextChangedListener(textWatcher);
        mEtRepassword.addTextChangedListener(textWatcher);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void showRegisterSuccess(String successMessage) {
        ToastUtil.showToast(successMessage);
    }

    @Override
    public void showRegisterFailed(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    @Override
    public void doSuccess(XBaseBean<User> user) {
        finish();
    }


    @OnClick(R.id.btn_register)
    public void onViewClicked() {
        String username = mEtUsername.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();
        String rePassword = mEtRepassword.getText().toString().trim();
        int tilUsernameCounterMaxLength = mTilUsername.getCounterMaxLength();
        int tilPasswordCounterMaxLength = mTilPassword.getCounterMaxLength();
        int tilRePasswordCounterMaxLength = mTilRepassword.getCounterMaxLength();
        presenter.register(username, password, rePassword, tilUsernameCounterMaxLength, tilPasswordCounterMaxLength, tilRePasswordCounterMaxLength);
    }
}
