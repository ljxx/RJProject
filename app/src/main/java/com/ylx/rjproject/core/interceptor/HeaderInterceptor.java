package com.ylx.rjproject.core.interceptor;

import android.text.TextUtils;
import android.util.ArrayMap;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * ========================================
 * <p/>
 * 版 权：蓝吉星讯 版权所有 （C） 2017
 * <p/>
 * 作 者：yanglixiang
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2017/7/3  下午5:12
 * <p/>
 * 描 述：请求头拦截器
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */

public class HeaderInterceptor implements Interceptor {
    private ArrayMap<String, String> headers;

    public HeaderInterceptor(ArrayMap<String, String> headers) {
        this.headers = headers;
    }

    @Override public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        //如果公共请求头不为空,则构建新的请求
        if (headers != null) {
            for (String key : headers.keySet()) {
                requestBuilder.addHeader(key, headers.get(key));
            }
        }
        Request request = requestBuilder.build();
        Response.Builder responseBuilder = chain.proceed(request).newBuilder();
        if (!TextUtils.isEmpty(request.cacheControl().toString())) {
            responseBuilder
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + request.cacheControl().maxAgeSeconds());
        }
        return responseBuilder.build();
    }
}
