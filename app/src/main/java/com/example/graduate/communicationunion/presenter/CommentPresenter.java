package com.example.graduate.communicationunion.presenter;

import android.content.Context;

import com.example.graduate.communicationunion.bean.Comment;
import com.example.graduate.communicationunion.presenter.callback.CommonCallback;
import com.example.graduate.communicationunion.web.ApiResponse;
import com.example.graduate.communicationunion.web.WebApiManager;
import com.gizwits.energy.android.lib.presenter.BasePresenter;
import com.gizwits.energy.android.lib.presenter.IDefaultListLoader;
import com.gizwits.energy.android.lib.web.base.WebRequestCancelHandler;

import org.xutils.common.Callback;

import java.util.List;

public class CommentPresenter extends BasePresenter {
    public CommentPresenter(Context context) {
        super(context);
    }

    public WebRequestCancelHandler getAllCommentByUserId(int userId, final IDefaultListLoader<Comment> handler) {
        final WebRequestCancelHandler webRequestCancelHandler = new WebRequestCancelHandler();
        handler.onRequestStarted();
        Callback.Cancelable cancelable = WebApiManager.getInstance().AllComment(userId, new Callback.CommonCallback<ApiResponse<List<Comment>>>() {
            @Override
            public void onSuccess(ApiResponse<List<Comment>> result) {
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

    public WebRequestCancelHandler publishComment(String user,  String content, int formuId, final CommonCallback handler) {
        final WebRequestCancelHandler webRequestCancelHandler = new WebRequestCancelHandler();
        handler.onRequestStarted();
        Callback.Cancelable cancelable = WebApiManager.getInstance().publishComment(user,content,formuId ,new Callback.CommonCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse result) {
                if (result.getCode().equals("200")) {
                    handler.onSuccess("发布成功");
                } else {
                    handler.onFail("发布失败");
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
}
