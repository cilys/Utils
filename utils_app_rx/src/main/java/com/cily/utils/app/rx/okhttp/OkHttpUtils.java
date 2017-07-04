package com.cily.utils.app.rx.okhttp;


import com.cily.utils.app.Init;
import com.cily.utils.app.utils.log.L;
import com.cily.utils.base.StrUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * user: cily
 * time: 2016/11/22
 * desc:
 */

public class OkHttpUtils {
    private final String TAG = this.getClass().getSimpleName();
    private static OkHttpUtils mUtils;
    private static OkHttpClient mOkHttpClient;

    private OkHttpUtils() {
        HttpLoggingInterceptor mLogInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                L.i(TAG, StrUtils.join("", message));
            }
        });

        if (Init.isDebug()) {
            mLogInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .writeTimeout(45, TimeUnit.SECONDS)
                .readTimeout(45, TimeUnit.SECONDS);
        if (Init.isDebug()) {
            builder.addInterceptor(mLogInterceptor);
        }
        mOkHttpClient = builder.build();
    }

    public static OkHttpUtils getInstance() {
        if (mUtils == null) {
            synchronized (OkHttpUtils.class) {
                if (mUtils == null) {
                    mUtils = new OkHttpUtils();
                }
            }
        }
        return mUtils;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

}
