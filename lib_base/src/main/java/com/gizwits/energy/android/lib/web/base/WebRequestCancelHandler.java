package com.gizwits.energy.android.lib.web.base;

import org.xutils.common.Callback;


public class WebRequestCancelHandler {
	private Callback.Cancelable cancelable;
	private volatile boolean isFinished;

	public WebRequestCancelHandler() {
	}

	public void cancel() {
		cancelable.cancel();
	}

	public boolean isCancelled() {
		return cancelable.isCancelled();
	}

	public boolean isFinished() {
		return isFinished;
	}

	public synchronized void setFinished(boolean finished) {
		this.isFinished = finished;
	}


	public synchronized void setCancelable(Callback.Cancelable cancelable) {
		this.cancelable = cancelable;
	}
}
