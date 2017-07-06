package com.cily.utils.app.rx.okhttp;

import android.content.Context;
import android.os.Environment;

import com.cily.utils.base.StrUtils;

import java.io.File;

/**
 * user:cily
 * time:2017/2/20
 * desc:
 */

public class NetConf {
    private static String baseUrl;
    public static void setBaseUrl(String url){
        baseUrl = url;
    }

    public static String getBaseUrl(){
        return baseUrl;
    }

    private static String cacheDir = null;

    public final static String getCacheDir(){
        if (cacheDir == null){
            cacheDir = StrUtils.join(Environment.getExternalStorageDirectory().getAbsolutePath(),
                    File.separator, "okHttpCache");
        }
        return cacheDir;
    }

    public final static void setCacheDir(Context cx){
        if (cx != null){
            cacheDir = cx.getApplicationContext().getCacheDir().getAbsolutePath();
        }
    }

    public final static String NET_ERROR_UNKNOW = "未知异常";
    public final static String NET_ERROR_TIME_OUT = "请求超时";
    public final static String NET_ERROR_SERVER_ERROR = "服务器异常";
    public final static String NET_CODE_SUCCESS = "0";
}
