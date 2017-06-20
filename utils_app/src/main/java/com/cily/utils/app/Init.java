package com.cily.utils.app;

import com.cily.utils.base.log.LogType;
import com.cily.utils.base.log.Logs;

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
        initLog(d ? LogType.ALL : LogType.NONE);
        initDebug(d);
    }
    public final static void setConsoleLog(boolean c){
        Logs.setConsoleLog(c);
    }
    public final static void setWriteLog(boolean w, String filePath, String fileName){
        Logs.setLogFile(w, filePath, fileName);
    }

    public final static void setShowToast(boolean s) {
        showToast = s;
    }
}
