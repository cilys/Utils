package com.cily.utils.app;

import android.app.Application;

import com.cily.utils.app.utils.AppUtils;
import com.cily.utils.app.utils.NetUtils;
import com.cily.utils.base.log.Logs;
import com.cily.utils.log.DbUtils;
import com.cily.utils.log.L;

/**
 * user:cily
 * time:2017/7/30
 * desc:
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Logs.setSysInfo(AppUtils.getAppName(this), AppUtils.getAppSign(this),
                AppUtils.getVersionName(this), AppUtils.getSystemVersion(),
                NetUtils.getActiveMacAddress(this), AppUtils.getDeviceBrand(),
                AppUtils.getSystemModel(), AppUtils.getSystemModel());

        DbUtils.init(this);
    }
}
