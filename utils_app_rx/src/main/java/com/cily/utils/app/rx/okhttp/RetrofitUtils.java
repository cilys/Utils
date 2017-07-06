package com.cily.utils.app.rx.okhttp;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * user: cily
 * time: 2016/11/22
 * desc:
 */

public class RetrofitUtils {

    private static Retrofit mRetrofit;

    private static RetrofitUtils mUtils;

    private RetrofitUtils(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(NetConf.getBaseUrl())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpUtils.getInstance().getOkHttpClient())
                .build();
    }

    /**
     * 获取Retrofit对象
     * @param reset
     * 如果需要设置超时时间，必须在此方法前设置
     * @return
     */
    public static RetrofitUtils getInstance(boolean reset) {
        if (reset) {
            mUtils = null;
            mRetrofit = null;
        } else {
            if (mUtils == null) {
                synchronized (RetrofitUtils.class) {
                    if (mUtils == null) {
                        mUtils = new RetrofitUtils();
                    }
                }
            }
        }
        return mUtils;
    }

    public Retrofit getRetrofit(){
        return mRetrofit;
    }
}
