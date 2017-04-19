package com.cily.utils.app;

import com.cily.utils.base.Logs;

/**
 * user:cily
 * time:2017/4/17
 * desc:
 */

public class Init {
    private static boolean debug = true;
    private static boolean showToast = true;

    public final static void initLog(int level) {
        Logs.setLevel(level);
    }

    public final static void initDebug(boolean d) {
        debug = d;
    }

    public final static boolean isDebug() {
        return debug;
    }

    public final static boolean isShowToast() {
        return showToast;
    }

    public final static void init(boolean d){
        initLog(d ? Logs.ALL : Logs.NONE);
        initDebug(d);
    }

    public final static void setShowToast(boolean s) {
        showToast = s;
    }
}
