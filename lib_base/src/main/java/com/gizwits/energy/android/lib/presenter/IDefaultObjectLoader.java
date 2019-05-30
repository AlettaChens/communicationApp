package com.gizwits.energy.android.lib.presenter;


public interface IDefaultObjectLoader<ObjectType> extends IRequestLifeCycleHandler {
	void onLoadFail(String msg);

	void onLoadSuccess(ObjectType object);
}
