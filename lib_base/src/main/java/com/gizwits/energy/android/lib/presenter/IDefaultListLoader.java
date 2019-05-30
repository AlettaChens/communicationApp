package com.gizwits.energy.android.lib.presenter;

import android.support.annotation.NonNull;

import java.util.List;


public interface IDefaultListLoader<ObjectType> extends IRequestLifeCycleHandler {
	void onLoadFail(String msg);

	void onLoadSuccess(@NonNull List<ObjectType> list);

	void onListEnd();
}
