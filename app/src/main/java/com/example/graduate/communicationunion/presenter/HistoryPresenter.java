package com.example.graduate.communicationunion.presenter;

import android.content.Context;

import com.example.graduate.communicationunion.bean.Book;
import com.example.graduate.communicationunion.bean.Info;
import com.example.graduate.communicationunion.bean.Like;
import com.example.graduate.communicationunion.web.ApiResponse;
import com.example.graduate.communicationunion.web.WebApiManager;
import com.gizwits.energy.android.lib.presenter.BasePresenter;
import com.gizwits.energy.android.lib.presenter.IDefaultListLoader;
import com.gizwits.energy.android.lib.presenter.IRequestLifeCycleHandler;
import com.gizwits.energy.android.lib.web.base.WebRequestCancelHandler;

import org.xutils.common.Callback;

import java.io.File;
import java.util.List;

public class HistoryPresenter extends BasePresenter {

    public HistoryPresenter(Context context) {
        super(context);
    }

    public WebRequestCancelHandler getAllLike( int userId,final IDefaultListLoader<Like> handler) {
        final WebRequestCancelHandler webRequestCancelHandler = new WebRequestCancelHandler();
        handler.onRequestStarted();
        Callback.Cancelable cancelable = WebApiManager.getInstance().allLike(userId,new Callback.CommonCallback<ApiResponse<List<Like>>>() {
            @Override
            public void onSuccess(ApiResponse<List<Like>> result) {
                if(result.getCode().equals("200")){
                    handler.onLoadSuccess(result.getData());
                }else{
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

    public WebRequestCancelHandler lookHistory(int newId, int userId, final IPublishHandler handler) {
        final WebRequestCancelHandler webRequestCancelHandler = new WebRequestCancelHandler();
        handler.onRequestStarted();
        Callback.Cancelable cancelable = WebApiManager.getInstance().publishlike(newId, userId, new Callback.CommonCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse result) {
                if (result.getCode().equals("200")) {
                    handler.onUpdateSuccess("成功");
                } else {
                    handler.onUpdateFail("失败");
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


    public interface IPublishHandler extends IRequestLifeCycleHandler {

        void onUpdateSuccess(String message);

        void onUpdateFail(String message);

        void onUpdateError(Throwable e);
    }
}
