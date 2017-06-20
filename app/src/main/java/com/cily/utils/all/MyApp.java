package com.cily.utils.all;

import android.app.Application;

import com.cily.utils.app.CrashHandler;

/**
 * user:cily
 * time:2017/6/16
 * desc:
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CrashHandler.getInstace().init(this);
    }
}
