package com.gizwits.energy.android.lib.base;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.gizwits.energy.android.lib.R;

import org.xutils.x;




public abstract class BaseDialog extends Dialog {
	public BaseDialog(Context context) {
		/*
		 * 构造函数设置Dialog无边框样式
		 */
		super(context, R.style.theme_no_frame);

	}

	@Override
	public void setContentView(View view) {
		super.setContentView(view);
		x.view().inject(this, getWindow().getDecorView());
	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		x.view().inject(this, getWindow().getDecorView());
	}

	@Override
	public void setContentView(View view, ViewGroup.LayoutParams params) {
		super.setContentView(view, params);
		x.view().inject(this, getWindow().getDecorView());
	}
}
