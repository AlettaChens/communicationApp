package com.example.graduate.communicationunion.web.parser;

import com.gizwits.energy.android.lib.utils.GsonUtil;
import com.gizwits.energy.android.lib.utils.LogUtils;
import com.google.gson.Gson;

import org.xutils.http.app.ResponseParser;
import org.xutils.http.request.UriRequest;

import java.lang.reflect.Type;

public class JsonParser implements ResponseParser {

	private final String TAG = getClass().getSimpleName();

	Gson gson = GsonUtil.createSecurityGson();

	@Override
	public void checkResponse(UriRequest request) throws Throwable {

	}

	@Override
	public Object parse(Type resultType, Class<?> resultClass, String result) throws Throwable {

		LogUtils.d(TAG, "result : " + result);

		Object o = gson.fromJson(result,resultClass);

		return o;
	}
}
