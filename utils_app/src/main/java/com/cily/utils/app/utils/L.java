package com.cily.utils.app.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import com.cily.utils.base.Logs;
import com.cily.utils.base.StrUtils;

import java.util.List;
import java.util.Set;

/**
 * user:cily
 * time:2017/2/23
 * desc:log工具
 */
public class L extends Logs {

    @SuppressLint("DefaultLocale")
    private final static String getStackTrace() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int index = 6;
        String className = stackTrace[index].getFileName();
        String methodName = stackTrace[index].getMethodName();
        int lineNumber = stackTrace[index].getLineNumber();
        String methodNameShort = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
        return String.format("[ # (%1$s:%2$s) # %3$s ] ", className, lineNumber, methodNameShort);
    }

    public final static void v(String tag, String msg) {
        if (isPrint(tag, msg, VERBOSE)) {
            Log.v(tag, getStackTrace() + msg);
        }
    }

    public final static void d(String tag, String msg) {
        if (isPrint(tag, msg, DEBUG)) {
            Log.d(tag, getStackTrace() + msg);
        }
    }

    public final static void i(String tag, String msg) {
        if (isPrint(tag, msg, INFO)) {
            Log.i(tag, getStackTrace() + msg);
        }
    }

    public final static void w(String tag, String msg) {
        if (isPrint(tag, msg, WARN)) {
            Log.w(tag, getStackTrace() + msg);
        }
    }

    public final static void e(String tag, String msg) {
        if (isPrint(tag, msg, ERROR)) {
            Log.e(tag, getStackTrace() + msg);
        }
    }

    public final static void printList(String tag, String tip, List<String> l){
        if (isPrint(tag, tip, INFO)){
            if (l != null && l.size() > 0){
                StringBuilder su = StrUtils.getStringBuilder();
                su.append(tip);
                su.append(" : ");
                for (String s : l){
                    su.append(s);
                    su.append(" ");
                }
                su.append(", total size = ");
                su.append(l.size());
                L.i(tag, getStackTrace() + su.toString());
            }
        }
    }

    public final static void printSet(String tag, String tip, Set<String> l){
        if (isPrint(tag, tip, INFO)){
            if (l != null && l.size() > 0){
                StringBuilder su = StrUtils.getStringBuilder();
                su.append(tip);
                su.append(" : ");
                for (String s : l){
                    su.append(s);
                    su.append(" ");
                }
                su.append(", total size = ");
                su.append(l.size());
                L.i(tag, getStackTrace() + su.toString());
            }
        }
    }

    private static boolean isPrint(String tag, String msg, int targetLevel) {
        return getLevel() >= targetLevel && tag != null && msg != null;
    }
}