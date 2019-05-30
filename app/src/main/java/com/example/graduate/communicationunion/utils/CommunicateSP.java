package com.example.graduate.communicationunion.utils;

import android.content.Context;

import com.gizwits.energy.android.lib.utils.BaseSPUtil;

public class CommunicateSP extends BaseSPUtil {
    public CommunicateSP(Context context) {
        super(context, "communicate");
    }

    public void putIsLogin(boolean isLogin) {
        putBoolean("isLogin", isLogin);
    }

    public Boolean getisLogin() {
        return getBoolean("isLogin", false);
    }

    public void putUserId(int userId) {
        putInt("userId", userId);
    }

    public int getUserId() {
        return getInt("userId", 0);
    }

    public void putNickName(String nickname) {
        putString("nickname", nickname);
    }

    public String getNickName() {
        return getString("nickname", "");
    }

    public void putPassword(String password) {
        putString("password", password);
    }

    public String getPassword() {
        return getString("password", "");
    }


    public void putSex(String sex) {
        putString("sex", sex);
    }

    public String getSex() {
        return getString("sex", "");
    }

    public void putAddress(String address) {
        putString("address", address);
    }

    public String getAddress() {
        return getString("address", "");
    }

    public void putAge(String age) {
        putString("age", age);
    }

    public String getAge() {
        return getString("age", "");
    }


    public void putPhone(String phone) {
        putString("phone", phone);
    }

    public String getPhone() {
        return getString("phone", "");
    }


    public void putAvatar(String avatar) {
        putString("avatar", avatar);
    }

    public String getAvatar() {
        return getString("avatar", "");
    }
}
