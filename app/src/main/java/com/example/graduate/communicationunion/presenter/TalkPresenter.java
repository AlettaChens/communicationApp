package com.example.graduate.communicationunion.presenter;

import android.content.Context;

import com.example.graduate.communicationunion.bean.Book;
import com.example.graduate.communicationunion.bean.Formu;
import com.example.graduate.communicationunion.presenter.callback.CommonCallback;
import com.example.graduate.communicationunion.web.ApiResponse;
import com.example.graduate.communicationunion.web.WebApiManager;
import com.gizwits.energy.android.lib.presenter.BasePresenter;
import com.gizwits.energy.android.lib.presenter.IDefaultListLoader;
import com.gizwits.energy.android.lib.presenter.IRequestLifeCycleHandler;
import com.gizwits.energy.android.lib.web.base.WebRequestCancelHandler;

import org.xutils.common.Callback;

import java.io.File;
import java.util.List;

public class TalkPresenter extends BasePresenter {

    public TalkPresenter(Context context) {
        super(context);
    }

    public WebRequestCancelHandler getAllTalk(int userId, final IDefaultListLoader<Formu> handler) {
        final WebRequestCancelHandler webRequestCancelHandler = new WebRequestCancelHandler();
        handler.onRequestStarted();
        Callback.Cancelable cancelable = WebApiManager.getInstance().AllTalk(userId, new Callback.CommonCallback<ApiResponse<List<Formu>>>() {
            @Override
            public void onSuccess(ApiResponse<List<Formu>> result) {
                if (result.getCode().equals("200")) {
                    handler.onLoadSuccess(result.getData());
                } else {
                    handler.onLoadFail("获取失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                handler.onLoadFail(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                handler.onLoadFail(cex.toString());
            }

            @Override
            public void onFinished() {
                webRequestCancelHandler.setFinished(true);
                handler.onRequestFinished();
            }
        });
        webRequestCancelHandler.setCancelable(cancelable);
        addCancelHandler(webRequestCancelHandler);
        return webRequestCancelHandler;
    }

    public WebRequestCancelHandler getAllTalkByUserName(String nickname, final IDefaultListLoader<Formu> handler) {
        final WebRequestCancelHandler webRequestCancelHandler = new WebRequestCancelHandler();
        handler.onRequestStarted();
        Callback.Cancelable cancelable = WebApiManager.getInstance().getTalkByName(nickname, new Callback.CommonCallback<ApiResponse<List<Formu>>>() {
            @Override
            public void onSuccess(ApiResponse<List<Formu>> result) {
                if (result.getCode().equals("200")) {
                    handler.onLoadSuccess(result.getData());
                } else {
                    handler.onLoadFail("获取失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                handler.onLoadFail(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                handler.onLoadFail(cex.toString());
            }

            @Override
            public void onFinished() {
                webRequestCancelHandler.setFinished(true);
                handler.onRequestFinished();
            }
        });
        webRequestCancelHandler.setCancelable(cancelable);
        addCancelHandler(webRequestCancelHandler);
        return webRequestCancelHandler;
    }

    public WebRequestCancelHandler publishTalk(String title, String content, String url, String name, final IPublishHandler handler) {
        final WebRequestCancelHandler webRequestCancelHandler = new WebRequestCancelHandler();
        handler.onRequestStarted();
        Callback.Cancelable cancelable = WebApiManager.getInstance().publishTalk(title, content, name, url, new Callback.CommonCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse result) {
                if (result.getCode().equals("200")) {
                    handler.onUpdateSuccess("发布成功");
                } else {
                    handler.onUpdateFail("发布失败");
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


    public WebRequestCancelHandler updateStar(int formuId,int userId ,String type, final CommonCallback commonCallback) {
        final WebRequestCancelHandler webRequestCancelHandler = new WebRequestCancelHandler();
        commonCallback.onRequestStarted();
        Callback.Cancelable cancelable = WebApiManager.getInstance().updateStar(formuId,userId, type, new Callback.CommonCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse result) {
                if (result.getCode().equals("200")) {
                } else {
                    commonCallback.onFail("更新失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                commonCallback.onError(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                commonCallback.onRequestFinished();
                webRequestCancelHandler.setFinished(true);
            }
        });
        webRequestCancelHandler.setCancelable(cancelable);
        addCancelHandler(webRequestCancelHandler);
        return webRequestCancelHandler;
    }


    public WebRequestCancelHandler deleteTalk(int id,final CommonCallback handler) {
        final WebRequestCancelHandler webRequestCancelHandler = new WebRequestCancelHandler();
        handler.onRequestStarted();
        Callback.Cancelable cancelable = WebApiManager.getInstance().deleteTalkById(id, new Callback.CommonCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse result) {
                if (result.getCode().equals("200")) {
                    handler.onSuccess("删除成功");
                } else {
                    handler.onFail("删除失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                handler.onError(ex);
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
    public interface IPublishHandler extends IRequestLifeCycleHandler {

        void onUpdateSuccess(String message);

        void onUpdateFail(String message);

        void onUpdateError(Throwable e);
    }
}
