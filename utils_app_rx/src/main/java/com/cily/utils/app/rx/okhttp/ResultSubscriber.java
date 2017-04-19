package com.cily.utils.app.rx.okhttp;

import com.cily.utils.base.StrUtils;

import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * user:cily
 * time:2017/2/22
 * desc:
 */

public abstract class ResultSubscriber<T> extends Subscriber<T>{
    @Override
    public void onCompleted() {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            onFailure(NetConf.NET_ERROR_TIME_OUT);
        } else if (e instanceof ResponseException) {
            onFailure(StrUtils.isEmpty(e.getMessage()) ? NetConf.NET_ERROR_SERVER_ERROR : e.getMessage());
        } else {
            onFailure(NetConf.NET_ERROR_UNKNOW + e.getMessage());
        }
    }

    public abstract void onSuccess(T t);
    public abstract void onFailure(String msg);
}
