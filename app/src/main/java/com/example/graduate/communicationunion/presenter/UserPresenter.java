package com.example.graduate.communicationunion.presenter;

import android.content.Context;

import com.example.graduate.communicationunion.bean.User;
import com.example.graduate.communicationunion.utils.CommunicateSP;
import com.example.graduate.communicationunion.web.ApiResponse;
import com.example.graduate.communicationunion.web.WebApiManager;
import com.gizwits.energy.android.lib.presenter.BasePresenter;
import com.gizwits.energy.android.lib.presenter.IRequestLifeCycleHandler;
import com.gizwits.energy.android.lib.web.base.WebRequestCancelHandler;


import org.xutils.common.Callback;

import java.io.File;

public class UserPresenter extends BasePresenter {
    private CommunicateSP communicateSP;

    public UserPresenter(Context context) {
        super(context);
        communicateSP = new CommunicateSP(context);
    }

    /**
     * 用户登录
     *
     * @param nickname
     * @param password
     * @param handler
     * @return
     */
    public WebRequestCancelHandler login(String nickname, String password, final ILoginHandler handler) {

        final WebRequestCancelHandler webRequestCancelHandler = new WebRequestCancelHandler();

        handler.onRequestStarted();

        Callback.Cancelable cancelable = WebApiManager.getInstance().login(nickname, password, new Callback.CommonCallback<ApiResponse<User>>() {
            @Override
            public void onSuccess(ApiResponse<User> result) {
                if (result == null) {
                    handler.onLoginFail("no data response");
                    return;
                }
                if (result.getCode().equals("200") && result.getData() != null) {
                    handler.onLoginSuccess();
                    User user = result.getData();
                    updateLoginInfo(user);
                } else {
                    handler.onLoginFail(result.getMessage());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                handler.onLoginError(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                handler.onRequestCanceled();
            }

            @Override
            public void onFinished() {
                handler.onRequestFinished();
                webRequestCancelHandler.setFinished(true);
            }
        });


        webRequestCancelHandler.setCancelable(cancelable);

        addCancelHandler(webRequestCancelHandler);

        return webRequestCancelHandler;
    }

    /**
     * 用户注册
     *
     * @param nickname
     * @param password
     * @param phone
     * @param handler
     * @return
     */
    public WebRequestCancelHandler register(String nickname, String password, String phone, final IRegisterHandler handler) {
        final WebRequestCancelHandler webRequestCancelHandler = new WebRequestCancelHandler();
        handler.onRequestStarted();
        Callback.Cancelable cancelable = WebApiManager.getInstance().register(nickname, password, phone, new Callback.CommonCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse result) {
                if (result.getCode().equals("200")) {
                    handler.onRegisterSuccess("注册成功");
                } else {
                    handler.onRegisterFail("注册失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                handler.onRegisterError(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                handler.onRequestCanceled();
            }

            @Override
            public void onFinished() {
                handler.onRequestFinished();
                webRequestCancelHandler.setFinished(true);
            }
        });
        webRequestCancelHandler.setCancelable(cancelable);
        addCancelHandler(webRequestCancelHandler);
        return webRequestCancelHandler;
    }


    /**
     * 用户更新图像
     *
     * @param avater
     * @param userId
     * @param handler
     * @return
     */
    public WebRequestCancelHandler updatAvatar(File avater, int userId, final IUpdateHandler handler) {
        final WebRequestCancelHandler webRequestCancelHandler = new WebRequestCancelHandler();
        handler.onRequestStarted();
        Callback.Cancelable cancelable = WebApiManager.getInstance().updateAvatar(avater, userId, new Callback.CommonCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse result) {
                if (result.getCode().equals("200")) {
                    handler.onUpdateSuccess(result.getData().toString());
                    communicateSP.putAvatar(result.getData().toString());
                } else {
                    handler.onUpdateFail("更新失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                handler.onUpdateError(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                handler.onRequestCanceled();
            }

            @Override
            public void onFinished() {
                handler.onRequestFinished();
                webRequestCancelHandler.setFinished(true);
            }
        });
        webRequestCancelHandler.setCancelable(cancelable);
        addCancelHandler(webRequestCancelHandler);
        return webRequestCancelHandler;
    }


    public WebRequestCancelHandler updateUser(final String nickname, final String sex, final String phone, final String age, final String address, int userId, final IUpdateHandler handler) {
        final WebRequestCancelHandler webRequestCancelHandler = new WebRequestCancelHandler();
        handler.onRequestStarted();
        Callback.Cancelable cancelable = WebApiManager.getInstance().updateUser(nickname, sex, phone, age, address, userId, new Callback.CommonCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse result) {
                if (result.getCode().equals("200")) {
                    handler.onUpdateSuccess("更新成功");
                    communicateSP.putAddress(address);
                    communicateSP.putAge(age);
                    communicateSP.putNickName(nickname);
                    communicateSP.putPhone(phone);
                    communicateSP.putSex(sex);
                } else {
                    handler.onUpdateFail("更新失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                handler.onUpdateError(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                handler.onRequestCanceled();
            }

            @Override
            public void onFinished() {
                handler.onRequestFinished();
                webRequestCancelHandler.setFinished(true);
            }
        });
        webRequestCancelHandler.setCancelable(cancelable);
        addCancelHandler(webRequestCancelHandler);
        return webRequestCancelHandler;
    }


    /**
     * 更新登录信息
     *
     * @param user
     */
    private void updateLoginInfo(User user) {
        communicateSP.putIsLogin(true);
        communicateSP.putUserId(user.getUserId());
        communicateSP.putNickName(user.getNickname());
        communicateSP.putPassword(user.getPassword());
        communicateSP.putAge(user.getAge());
        communicateSP.putAddress(user.getAddress());
        communicateSP.putAvatar(user.getAvatar_url());
        communicateSP.putSex(user.getSex());
        communicateSP.putPhone(user.getPhone());
    }

    /**
     * 清楚所有的缓存数据
     */
    public void clearLoginInfo() {
        communicateSP.putIsLogin(false);
        communicateSP.putUserId(0);
        communicateSP.putNickName("");
        communicateSP.putPassword("");
        communicateSP.putAge("");
        communicateSP.putAddress("");
        communicateSP.putAvatar("");
        communicateSP.putSex("");
        communicateSP.putPhone("");
    }

    /***用户相关的回调接口***/
    public interface ILoginHandler extends IRequestLifeCycleHandler {

        void onLoginSuccess();

        void onLoginFail(String message);

        void onLoginError(Throwable e);
    }

    public interface IRegisterHandler extends IRequestLifeCycleHandler {

        void onRegisterSuccess(String message);

        void onRegisterFail(String message);

        void onRegisterError(Throwable e);
    }


    public interface IUpdateHandler extends IRequestLifeCycleHandler {

        void onUpdateSuccess(String message);

        void onUpdateFail(String message);

        void onUpdateError(Throwable e);
    }
}
