package com.cily.utils.app.rx.okhttp;

import rx.functions.Func1;

/**
 * user:cily
 * time:2017/2/22
 * desc:
 */

public class BaseEntity<T> implements Func1<BaseResponseBean<T>, T> {
    @Override
    public T call(BaseResponseBean<T> b) {
        if (!NetConf.NET_CODE_SUCCESS.equals(b.getCode())){
            throw new ResponseException(b.getMsg() == null ? NetConf.NET_ERROR_UNKNOW : b.getMsg());
        }
        return b.getData();
    }
}
