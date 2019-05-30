package com.example.graduate.communicationunion.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.graduate.communicationunion.R;
import com.example.graduate.communicationunion.dialog.UpdateUserInfoDialog;
import com.example.graduate.communicationunion.presenter.UserPresenter;
import com.example.graduate.communicationunion.utils.CommunicateSP;
import com.example.graduate.communicationunion.utils.MessageUtils;
import com.gizwits.energy.android.lib.base.BaseActivity;


import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import de.hdodenhof.circleimageview.CircleImageView;

@ContentView(R.layout.avtivity_update_user_info)
public class UpdateUserInfoActivity extends BaseActivity {
    private UpdateInfoHander updateInfoHander;
    @ViewInject(R.id.rl_nick)
    private RelativeLayout rl_nick;
    @ViewInject(R.id.rl_sex)
    private RelativeLayout rl_sex;
    @ViewInject(R.id.rl_phone)
    private RelativeLayout rl_phone;
    @ViewInject(R.id.rl_address)
    private RelativeLayout rl_address;
    @ViewInject(R.id.rl_age)
    private RelativeLayout rl_age;
    @ViewInject(R.id.ci_user)
    private CircleImageView ci_user;
    @ViewInject(R.id.tv_nick)
    private TextView tv_nick;
    @ViewInject(R.id.tv_address)
    private TextView tv_address;
    @ViewInject(R.id.tv_sex)
    private TextView tv_sex;
    @ViewInject(R.id.tv_phone)
    private TextView tv_phone;
    @ViewInject(R.id.tv_age)
    private TextView tv_age;
    @ViewInject(R.id.btn_update)
    private TextView btn_update;

    @ViewInject(R.id.iv_back)
    private ImageView iv_back;

    private UpdateUserInfoDialog updateUserInfoDialog;
    private UserPresenter userPresenter;
    private CommunicateSP communicateSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        communicateSP = new CommunicateSP(UpdateUserInfoActivity.this);
        updateInfoHander = new UpdateInfoHander();
        userPresenter = new UserPresenter(UpdateUserInfoActivity.this);
    }

    @Override
    public void onResume() {
        super.onResume();
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.user_default)
                .error(R.mipmap.user_default);
        Glide.with(this).load(communicateSP.getAvatar()).apply(options).into(ci_user);
        tv_address.setText(communicateSP.getAddress());
        tv_age.setText(communicateSP.getAge());
        tv_nick.setText(communicateSP.getNickName());
        tv_phone.setText(communicateSP.getPhone());
        tv_sex.setText(communicateSP.getSex());
    }

    @Event({R.id.rl_nick, R.id.rl_sex, R.id.rl_phone, R.id.rl_address, R.id.rl_age, R.id.btn_update, R.id.iv_back})
    private void OnUpdateClick(View view) {
        switch (view.getId()) {
            case R.id.rl_nick: {
                updateUserInfoDialog = new UpdateUserInfoDialog(UpdateUserInfoActivity.this, updateInfoHander, "用户昵称");
                updateUserInfoDialog.show();
                break;
            }
            case R.id.rl_sex: {
                updateUserInfoDialog = new UpdateUserInfoDialog(UpdateUserInfoActivity.this, updateInfoHander, "用户性别");
                updateUserInfoDialog.show();
                break;
            }
            case R.id.rl_phone: {
                updateUserInfoDialog = new UpdateUserInfoDialog(UpdateUserInfoActivity.this, updateInfoHander, "用户电话");
                updateUserInfoDialog.show();
                break;
            }
            case R.id.rl_address: {
                updateUserInfoDialog = new UpdateUserInfoDialog(UpdateUserInfoActivity.this, updateInfoHander, "用户地址");
                updateUserInfoDialog.show();
                break;
            }
            case R.id.rl_age: {
                updateUserInfoDialog = new UpdateUserInfoDialog(UpdateUserInfoActivity.this, updateInfoHander, "用户年龄");
                updateUserInfoDialog.show();
                break;
            }
            case R.id.btn_update: {
                userPresenter.updateUser(tv_nick.getText().toString(), tv_sex.getText().toString(), tv_phone.getText().toString(), tv_age.getText().toString(), tv_address.getText().toString(), communicateSP.getUserId(), new UpdateHandle());
                break;
            }
            case R.id.iv_back: {
                onBackPressed();
                break;
            }
        }
    }

    public class UpdateInfoHander implements UpdateUserInfoDialog.UpdateCompelet {
        @Override
        public void onCompelet(String type, String info) {
            switch (type) {
                case "用户昵称": {
                    tv_nick.setText(info);
                    break;
                }
                case "用户性别": {
                    tv_sex.setText(info);
                    break;
                }
                case "用户电话": {
                    tv_phone.setText(info);
                    break;
                }
                case "用户地址": {
                    tv_address.setText(info);
                    break;
                }
                case "用户年龄": {
                    tv_age.setText(info);
                    break;
                }
            }
        }
    }

    public class UpdateHandle implements UserPresenter.IUpdateHandler {
        @Override
        public void onUpdateSuccess(String message) {
            MessageUtils.showShortToast(UpdateUserInfoActivity.this, message);
            refreshUserInfo();
        }

        @Override
        public void onUpdateFail(String message) {
            MessageUtils.showShortToast(UpdateUserInfoActivity.this, message);
        }

        @Override
        public void onUpdateError(Throwable e) {
            MessageUtils.showShortToast(UpdateUserInfoActivity.this, e.toString());
        }

        @Override
        public void onRequestStarted() {
            showLoadingDialog("更新数据中...");
        }

        @Override
        public void onRequestCanceled() {

        }

        @Override
        public void onRequestFinished() {
            dismissLoadingDialog();
        }
    }

    private void refreshUserInfo() {
        tv_address.setText(communicateSP.getAddress());
        tv_age.setText(communicateSP.getAge());
        tv_nick.setText(communicateSP.getNickName());
        tv_phone.setText(communicateSP.getPhone());
        tv_sex.setText(communicateSP.getSex());
    }

}
