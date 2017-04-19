package com.cily.utils.app.rx.okhttp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * user: cily
 * time: 2016/11/26
 * desc: 添加请求头拦截器
 */

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .build();

        return chain.proceed(request);
    }
}
