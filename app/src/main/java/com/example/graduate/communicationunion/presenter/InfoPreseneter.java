package com.example.graduate.communicationunion.presenter;

import android.content.Context;

import com.example.graduate.communicationunion.bean.Info;
import com.example.graduate.communicationunion.web.ApiResponse;
import com.example.graduate.communicationunion.web.WebApiManager;
import com.gizwits.energy.android.lib.presenter.BasePresenter;
import com.gizwits.energy.android.lib.presenter.IDefaultListLoader;
import com.gizwits.energy.android.lib.web.base.WebRequestCancelHandler;

import org.xutils.common.Callback;

import java.util.List;

public class InfoPreseneter extends BasePresenter {
    public InfoPreseneter(Context context) {
        super(context);
    }

    public WebRequestCancelHandler recommend(int userId, final IDefaultListLoader<Info> handler) {
        final WebRequestCancelHandler webRequestCancelHandler = new WebRequestCancelHandler();
        handler.onRequestStarted();
        Callback.Cancelable cancelable = WebApiManager.getInstance().recommend(userId, new Callback.CommonCallback<ApiResponse<List<Info>>>() {
            @Override
            public void onSuccess(ApiResponse<List<Info>> result) {
                if(result.getCode().equals("200")){
                    handler.onLoadSuccess(result.getData());
                }else{
                    handler.onLoadFail("推荐失败");
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

    public WebRequestCancelHandler showTypeList(String type, final IDefaultListLoader<Info> handler) {
        final WebRequestCancelHandler webRequestCancelHandler = new WebRequestCancelHandler();
        handler.onRequestStarted();
        Callback.Cancelable cancelable = WebApiManager.getInstance().showInfoListByType(type, new Callback.CommonCallback<ApiResponse<List<Info>>>() {
            @Override
            public void onSuccess(ApiResponse<List<Info>> result) {
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




}
