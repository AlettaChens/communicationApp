package com.gizwits.energy.android.lib.web.base;


public interface WebResponseParser<T> {
    T parse(WebResponse<T> webResponse) throws Exception;
}
