package com.gizwits.energy.android.lib.web.base;


import com.gizwits.energy.android.lib.base.AbstractConstantClass;


public abstract class BaseWebAPI extends AbstractConstantClass {

	public static class BaseParameters extends AbstractConstantClass {

		public static final String PASSWORD = "password";
		public static final String CURRENT_PWD = "currentPwd";
		public static final String NEW_PWD = "newPwd";
	}


	public static class BaseErrorCode extends AbstractConstantClass {

		// RESULT_INVALID(-4,"返回的数据无效"),
		public static final String CODE_RESULT_INVALID = "-4";
		// FAIL(-1,"处理失败"),
		// 服务器请求失败
		public static final String CODE_FAIL = "-1";
		// SUCCESS(0,"成功"),
		public static final String CODE_SUCCESS = "200";
	}

	public static class BaseResponse extends AbstractConstantClass {
		public static final String CODE = "code";
		public static final String MESSAGE = "message";
		public static final String RESULT = "result";
	}
}
