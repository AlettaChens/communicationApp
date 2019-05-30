package com.example.graduate.communicationunion.presenter.callback;

import com.gizwits.energy.android.lib.presenter.IRequestLifeCycleHandler;

public interface CommonCallback extends IRequestLifeCycleHandler {
    void onSuccess(String message);

    void onFail(String message);

    void onError(Throwable e);
}
