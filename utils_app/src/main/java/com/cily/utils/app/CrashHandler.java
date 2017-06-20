package com.cily.utils.app;

import android.content.Context;
import android.content.Intent;

import com.cily.utils.app.utils.log.L;

/**
 * user:cily
 * time:2017/6/16
 * desc:
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private final String TAG = this.getClass().getSimpleName();
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Context cx;

    private static class CH{
        static CrashHandler ch = new CrashHandler();
    }

    private CrashHandler(){}

    public static CrashHandler getInstace(){
        return CH.ch;
    }

    public void init(Context context) {
        cx = context;
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (!handleException(e) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(t, e);
        } else {
            //退出程序
            restartApp(cx);

            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }

        L.printException(ex);

        return true;
    }

    private void restartApp(Context cx){
        final Intent intent = cx.getPackageManager().getLaunchIntentForPackage(
                cx.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        cx.startActivity(intent);
    }

}
