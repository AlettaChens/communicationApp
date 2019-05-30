package com.gizwits.energy.android.lib.presenter;


public interface IRequestLifeCycleHandler {

	void onRequestStarted();

	void onRequestCanceled();

	void onRequestFinished();
}
