package com.example.graduate.communicationunion.activity;


import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.graduate.communicationunion.R;
import com.example.graduate.communicationunion.presenter.UserPresenter;
import com.example.graduate.communicationunion.utils.MessageUtils;
import com.gizwits.energy.android.lib.base.BaseActivity;


import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {
    @ViewInject(R.id.btn_reg_now)
    private Button btn_reg_now;
    @ViewInject(R.id.et_username)
    private EditText et_username;
    @ViewInject(R.id.et_password)
    private EditText et_password;
    @ViewInject(R.id.et_comfirm_psd)
    private EditText et_comfirm_psd;
    @ViewInject(R.id.et_phone)
    private EditText et_phone;

    private RegisterHandler registerHandler;
    private UserPresenter userPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userPresenter = new UserPresenter(RegisterActivity.this);
        registerHandler = new RegisterHandler();
        btn_reg_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPresenter.register(et_username.getText().toString(), et_password.getText().toString(), et_phone.getText().toString(), registerHandler);
            }
        });
    }

    public class RegisterHandler implements UserPresenter.IRegisterHandler {
        @Override
        public void onRegisterSuccess(String message) {
            MessageUtils.showShortToast(RegisterActivity.this, message);
        }

        @Override
        public void onRegisterFail(String message) {
            MessageUtils.showShortToast(RegisterActivity.this, message);
        }

        @Override
        public void onRegisterError(Throwable e) {
            MessageUtils.showShortToast(RegisterActivity.this, e.toString());
        }

        @Override
        public void onRequestStarted() {
            showLoadingDialog("用户注册中...");

        }

        @Override
        public void onRequestCanceled() {

        }

        @Override
        public void onRequestFinished() {
            dismissLoadingDialog();
        }
    }
}
