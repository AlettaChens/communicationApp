package com.example.graduate.communicationunion.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.graduate.communicationunion.R;

import com.example.graduate.communicationunion.presenter.UserPresenter;
import com.example.graduate.communicationunion.utils.MessageUtils;
import com.gizwits.energy.android.lib.base.BaseActivity;


import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {
    @ViewInject(R.id.btn_login)
    private Button btn_login;
    @ViewInject(R.id.btn_register)
    private TextView btn_register;
    @ViewInject(R.id.et_username)
    private EditText et_username;
    @ViewInject(R.id.et_password)
    private EditText et_password;

    private UserPresenter userPresenter;
    private LoginHandler loginHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userPresenter = new UserPresenter(LoginActivity.this);
        loginHandler = new LoginHandler();

    }

    @Event({R.id.btn_login, R.id.btn_register})
    private void OnLoginClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login: {
                if (!TextUtils.isEmpty(et_username.getText().toString()) && !TextUtils.isEmpty(et_password.getText().toString())) {
                    userPresenter.login(et_username.getText().toString(), et_password.getText().toString(), loginHandler);
                }
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                finish();
                break;
            }
            case R.id.btn_register: {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            }
        }
    }

    public class LoginHandler implements UserPresenter.ILoginHandler {
        @Override
        public void onLoginSuccess() {
            MessageUtils.showShortToast(LoginActivity.this, "登陆成功");
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        @Override
        public void onLoginFail(String message) {
            MessageUtils.showShortToast(LoginActivity.this, message);
            Log.e("info", message);
        }

        @Override
        public void onLoginError(Throwable e) {
            MessageUtils.showShortToast(LoginActivity.this, e.toString());
            Log.e("info", e.toString());
        }

        @Override
        public void onRequestStarted() {
            showLoadingDialog("登录中...");

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
