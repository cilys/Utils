package com.cily.utils.log;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cily.utils.base.HttpUtils;
import com.cily.utils.base.StrUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * user:cily
 * time:2017/6/16
 * desc:
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private final String TAG = this.getClass().getSimpleName();
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Context cx;
    private boolean restartApp = false;
    private boolean postError = false;
    private static CrashHandler ch;


    private CrashHandler(boolean restartApp, boolean postError){
        this.restartApp = restartApp;
        this.postError = postError;
    }

    public static CrashHandler getInstace(boolean restartApp){
        if (ch == null){
            synchronized (CrashHandler.class) {
                if (ch == null) {
                    ch = new CrashHandler(restartApp, false);
                }
            }
        }
        return ch;
    }

    public static CrashHandler getInstace(boolean restartApp, boolean postError){
        if (ch == null){
            synchronized (CrashHandler.class) {
                if (ch == null) {
                    ch = new CrashHandler(restartApp, postError);
                }
            }
        }
        return ch;
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
            if (restartApp) {
                restartApp(cx);
            }
//            LogBean b = new LogBean(LogType.ERROR, "Crash",
//                    StreamToStr.throwableToStr(e), AppUtils.getVersionName(cx),
//                    Build.VERSION.SDK, )
//            BmobLogError.postError("classes/error_log",
//                    "6e4f6350d5d05748c2acccd0d1b6d6d7",
//                    "a44ed5bdea6e5ebff6001feecf145d9c", );

            if (postError){
                if (StrUtils.isEmpty(logUrl)){
                    return;
                }
                List<LogBean> l = DbUtils.search(10);
                if (l == null || l.size() < 1){
                    return;
                }
                JSONArray ja = new JSONArray();
                for (LogBean b : l){
                    JSONObject jo = new JSONObject();
                    Field[] fs = b.getClass().getDeclaredFields();
                    if (fs != null){
                        for (Field f : fs){
                            try {
                                jo.put("\"" + f.getName() + "\"", f.get(f.getName()));
                            } catch (JSONException e1) {
                                logEx("JSONException", e1);
                            } catch (IllegalAccessException e1) {
                                logEx("IllegalAccessException", e1);
                            }
                        }
                    }
                    ja.put(jo);
                }
                if (ja == null || ja.length() < 1){
                    return;
                }
                Map<String, String>param = new HashMap<>();
                param.put(HttpUtils.JSON_BODY, ja.toString());
                try {
                    HttpUtils.post(logUrl, false, header, param);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    private boolean debug = false;

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    private void logEx(String msg, Throwable t){
        if (t == null){
            return;
        }
        if (debug){
            Log.e(TAG, "" + msg, t);
        }
    }

    private String logUrl;
    private Map<String, String>header;

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl;
    }

    public void setHeader(Map<String, String> map){
        if (map == null){
            return;
        }
        header = null;

        header = map;
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
