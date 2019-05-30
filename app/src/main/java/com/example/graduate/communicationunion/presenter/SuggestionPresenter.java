package com.example.graduate.communicationunion.presenter;

import android.content.Context;

import com.example.graduate.communicationunion.web.ApiResponse;
import com.example.graduate.communicationunion.web.WebApiManager;
import com.gizwits.energy.android.lib.presenter.BasePresenter;
import com.gizwits.energy.android.lib.presenter.IRequestLifeCycleHandler;
import com.gizwits.energy.android.lib.web.base.WebRequestCancelHandler;

import org.xutils.common.Callback;

public class SuggestionPresenter extends BasePresenter {

    public SuggestionPresenter(Context context) {
        super(context);
    }
    public WebRequestCancelHandler suggestion(String url, String nickname, String suggestion, final ISuggestionHandler handler) {
        final WebRequestCancelHandler webRequestCancelHandler = new WebRequestCancelHandler();
        handler.onRequestStarted();
        Callback.Cancelable cancelable = WebApiManager.getInstance().Suggestion(url, nickname,suggestion,new Callback.CommonCallback<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse result) {
                if (result.getCode().equals("200")) {
                    handler.onSuggestionSuccess("发布成功");
                } else {
                    handler.onSuggestionFail("发布失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                handler.onSuggestionError(ex);
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

    public interface ISuggestionHandler extends IRequestLifeCycleHandler {

        void onSuggestionSuccess(String message);

        void onSuggestionFail(String message);

        void onSuggestionError(Throwable e);
    }
}
