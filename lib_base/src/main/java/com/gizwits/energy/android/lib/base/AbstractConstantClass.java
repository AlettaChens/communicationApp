package com.gizwits.energy.android.lib.base;


public abstract class AbstractConstantClass {
	protected AbstractConstantClass() {
		throw new Error("don't instantiation this constant class: " + this.getClass().getSimpleName());
	}
}
