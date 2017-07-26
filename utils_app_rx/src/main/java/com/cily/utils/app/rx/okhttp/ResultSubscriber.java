package com.cily.utils.app.rx.okhttp;

import android.text.TextUtils;

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
            onFailure(NetConf.CODE_ERROR_TIME_OUT, NetConf.ERROR_TIME_OUT);
        } else if (e instanceof ResponseException) {
            onFailure(((ResponseException) e).getErrorCode(), StrUtils.isEmpty(e.getMessage()) ? NetConf.ERROR_SERVER_ERROR : e.getMessage());
        } else {
            if (e.getMessage() != null && e.getMessage().contains("Connection refused")) {
                onFailure(NetConf.CODE_ERROR_CONNECTION_REFUSED, NetConf.ERROR_CONNECTION_REFUSED);
            } else {
                onFailure(NetConf.CODE_ERROR_UNKNOW,
                        TextUtils.isEmpty(e.getMessage()) ? NetConf.ERROR_UNKNOW : e.getMessage());
            }
        }
    }

    public abstract void onSuccess(T t);
    public abstract void onFailure(String errorCode, String msg);
}
