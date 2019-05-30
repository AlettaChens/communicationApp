package com.example.graduate.communicationunion.web;


import com.example.graduate.communicationunion.web.parser.ApiResponseParser;

import org.xutils.http.annotation.HttpResponse;

@HttpResponse(parser = ApiResponseParser.class)
public class ApiResponse<T> {
	private String code = "";
	private String message = "";
	private T data;


	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}

	@Override
	public String toString() {
		StringBuilder stb = new StringBuilder();

		stb.append("{ ").append("code : ").append(code).append(", ").append("message : ").append(message).append(", ").append("data : ").append(data).append("" +
				" }");

		return stb.toString();
	}
}
