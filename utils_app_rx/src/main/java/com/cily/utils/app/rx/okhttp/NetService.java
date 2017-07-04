package com.cily.utils.app.rx.okhttp;


import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * user:cily
 * time:2017/2/20
 * desc:
 */

public interface NetService {

    @GET
    Observable<BaseResponseBean> get(@Url String url, @HeaderMap Map<String, String> map_header, @QueryMap Map<String, String> map);

    @POST
    Observable<BaseResponseBean> post(@Url String url, @HeaderMap Map<String, String> map_header, @QueryMap Map<String, String> map);

    @POST
    Observable<BaseResponseBean> postForm(@Url String url, @HeaderMap Map<String, String> map_header, @QueryMap Map<String, String> map, @Part("image") MultipartBody body);
}
