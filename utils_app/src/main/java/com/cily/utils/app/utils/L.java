package com.cily.utils.app.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import com.cily.utils.base.io.StreamToStr;
import com.cily.utils.base.log.LogType;
import com.cily.utils.base.log.Logs;
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
        if (isPrint(tag, msg, LogType.VERBOSE)) {
            String m = getStackTrace() + msg;

            if (isConsoleLog()) {
                Log.v(tag, m);
            }

            if (isWriteLog()){
                writeLog(LogType.TYPE_VERBOSE, tag, m);
            }
            m = null;
        }
    }

    public final static void d(String tag, String msg) {
        if (isPrint(tag, msg, LogType.DEBUG)) {
            String m = getStackTrace() + msg;

            if (isConsoleLog()) {
                Log.d(tag, m);
            }

            if (isWriteLog()){
                writeLog(LogType.TYPE_DEBUG, tag, m);
            }
            m = null;
        }
    }

    public final static void i(String tag, String msg) {
        if (isPrint(tag, msg, LogType.INFO)) {
            String m = getStackTrace() + msg;

            if (isConsoleLog()) {
                Log.i(tag, m);
            }

            if (isWriteLog()){
                writeLog(LogType.TYPE_INFO, tag, m);
            }
            m = null;
        }
    }

    public final static void w(String tag, String msg) {
        w(tag, msg, null);
    }

    public final static void w(String tag, String msg, Throwable e){
        if (isPrint(tag, msg, LogType.WARN)) {
            String m = getStackTrace() + msg;

            if (isConsoleLog()) {
                if (e == null) {
                    Log.w(tag, m);
                }else {
                    Log.w(tag, m, e);
                }
            }

            if (isWriteLog()){
                if (e == null) {
                    writeLog(LogType.TYPE_WARN, tag, m);
                }else {
                    writeLog(LogType.TYPE_WARN, tag, m + StreamToStr.throwableToStr(e));
                }
            }
            m = null;
        }
    }

    public final static void e(String tag, String msg) {
        if (isPrint(tag, msg, LogType.ERROR)) {
            String m = getStackTrace() + msg;
            if (isConsoleLog()) {
                Log.e(tag, m);
            }

            if (isWriteLog()){
                writeLog(LogType.TYPE_ERROR, tag, m);
            }
            m = null;
        }
    }

    public final static void printList(String tag, String tip, List<String> l){
        if (isPrint(tag, tip, LogType.INFO)){
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

                String msg = getStackTrace() + su.toString();
                if (isConsoleLog()) {
                    L.i(tag, msg);
                }
                if (isWriteLog()){
                    writeLog(LogType.TYPE_INFO, tag, msg);
                }
                msg = null;
            }
        }
    }

    public final static void printSet(String tag, String tip, Set<String> l){
        if (isPrint(tag, tip, LogType.INFO)){
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

                String msg = getStackTrace() + su.toString();
                if (isConsoleLog()) {
                    L.i(tag, msg);
                }
                if (isWriteLog()){
                    writeLog(LogType.TYPE_INFO, tag, msg);
                }
                msg = null;
            }
        }
    }

    private static boolean isPrint(String tag, String msg, int targetLevel) {
        return getLevel() >= targetLevel && tag != null && msg != null;
    }
}