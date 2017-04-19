package com.cily.utils.app.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.cily.utils.base.StrUtils;

import java.util.Set;

/**
 * user:cily
 * time:2017/2/23
 * desc:SharedPreferences工具
 */
public class SpUtils {
    private final static String DEFAULT_SP_FILE_NAME = "sp_file";
    private static String spFileName = DEFAULT_SP_FILE_NAME;
    private final static String TAG = "SpUtils";

    public final static String getStr(Context cx, String key, String defaultValue) {
        SharedPreferences sp = sp(cx);
        String s = null;
        if (sp != null) {
            s = sp.getString(key, defaultValue);
        } else {
            s = defaultValue;
        }
        L.v(TAG, StrUtils.join("getStr: cx = ", cx, "<--->key = ", key, "<--->value = ", s));
        return s;
    }

    public final static void putStr(Context cx, String key, String value) {
        SharedPreferences sp = sp(cx);
        if (sp != null) {
            SharedPreferences.Editor ed = sp.edit();
            ed.putString(key, value);
            ed.commit();
        }
        L.v(TAG, StrUtils.join("putStr: cx = ", cx, "<--->key = ", key, "<--->value = ", value));
    }

    public final static int getInt(Context cx, String key, int defaultValue) {
        SharedPreferences sp = sp(cx);
        int n = -1;
        if (sp != null) {
            n = sp.getInt(key, defaultValue);
        } else {
            n = defaultValue;
        }
        L.v(TAG, StrUtils.join("getInt: cx = ", cx, "<--->key = ", key, "<--->value = ", n));
        return n;
    }

    public final static void putInt(Context cx, String key, int value) {
        SharedPreferences sp = sp(cx);
        if (sp != null) {
            SharedPreferences.Editor ed = sp.edit();
            ed.putInt(key, value);
            ed.commit();
        }
        L.v(TAG, StrUtils.join("putInt: cx = ", cx, "<--->key = ", key, "<--->value = ", value));
    }

    public final static boolean getBoolean(Context cx, String key, boolean defaultValue) {
        SharedPreferences sp = sp(cx);
        boolean v = false;
        if (sp != null) {
            v = sp.getBoolean(key, defaultValue);
        } else {
            v = defaultValue;
        }
        L.v(TAG, StrUtils.join("getBoolean: cx = ", cx, "<--->key = ", key, "<--->value = ", v));
        return v;
    }

    public final static void putBoolean(Context cx, String key, boolean value) {
        SharedPreferences sp = sp(cx);
        if (sp != null) {
            SharedPreferences.Editor ed = sp.edit();
            ed.putBoolean(key, value);
            ed.commit();
        }
        L.v(TAG, StrUtils.join("putBoolean: cx = ", cx, "<--->key = ", key, "<--->value = ", value));
    }

    public final static float getFloat(Context cx, String key, float defaultValue) {
        SharedPreferences sp = sp(cx);
        float v = -1f;
        if (sp != null) {
            v = sp.getFloat(key, defaultValue);
        } else {
            v = defaultValue;
        }
        L.v(TAG, StrUtils.join("getFloat: cx = ", cx, "<--->key = ", key, "<--->value = ", v));
        return v;
    }

    public final static void putFloat(Context cx, String key, float value) {
        SharedPreferences sp = sp(cx);
        if (sp != null) {
            SharedPreferences.Editor ed = sp.edit();
            ed.putFloat(key, value);
            ed.commit();
        }
        L.v(TAG, StrUtils.join("putFloat: cx = ", cx, "<--->key = ", key, "<--->value = ", value));
    }

    public final static long getLong(Context cx, String key, long defaultValue) {
        SharedPreferences sp = sp(cx);
        long v = -1L;
        if (sp != null) {
            v = sp.getLong(key, defaultValue);
        } else {
            v = defaultValue;
        }
        L.v(TAG, StrUtils.join("getLong: cx = ", cx, "<--->key = ", key, "<--->value = ", v));
        return v;
    }

    public final static void putLong(Context cx, String key, long value) {
        SharedPreferences sp = sp(cx);
        if (sp != null) {
            SharedPreferences.Editor ed = sp.edit();
            ed.putLong(key, value);
            ed.commit();
        }
        L.v(TAG, StrUtils.join("putLong: cx = ", cx, "<--->key = ", key, "<--->value = ", value));
    }

    @SuppressLint("NewApi")
    public final static Set<String> getSet(Context cx, String key, Set<String> defaultValue) {
        SharedPreferences sp = sp(cx);
        Set<String> v = null;
        if (sp != null) {
            v = sp.getStringSet(key, defaultValue);
        } else {
            v = defaultValue;
        }
        L.v(TAG, StrUtils.join("getSet: cx = ", cx, "<--->key = ", key, "<--->value = ", v));
        return v;
    }

    @SuppressLint("NewApi")
    public final static void putSet(Context cx, String key, Set<String> value) {
        SharedPreferences sp = sp(cx);
        if (sp != null) {
            SharedPreferences.Editor ed = sp.edit();
            ed.putStringSet(key, value);
            ed.commit();
        }

        if (L.getLevel() <= L.VERBOSE) {
            if (value == null) {
                L.v(TAG, StrUtils.join("putSet: cx = ", cx, "<--->key = ", key, "<--->value = ", value));
            } else {
                StringBuilder su = new StringBuilder();
                for (String s : value) {
                    su.append(s);
                    su.append("---");
                }
                L.v(TAG, StrUtils.join("putSet: cx = ", cx, "<--->key = ", key, "<--->value = ", su.toString()));
            }
        }
    }

    private final static SharedPreferences sp(Context cx) {
        if (TextUtils.isEmpty(spFileName)) {
            spFileName = DEFAULT_SP_FILE_NAME;
        }

        if (cx == null) {
            return null;
        }

        return cx.getSharedPreferences(spFileName, Context.MODE_PRIVATE);
    }

    public final static void setSpFileName(String spFileName) {
        SpUtils.spFileName = spFileName;
    }

}
