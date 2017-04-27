package com.cily.utils.app.utils;


import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * user:cily
 * time:2017/1/19
 * desc:toast工具
 */
public class ToastUtils {
    private final static String TAG = "ToastUtils";
    private static Toast to;

    public final static void showToast(Context cx, String str, boolean debug) {
        if (!debug) {
            L.d(TAG, "This is not debug model!");
            return;
        }

        if (str == null) {
            L.d(TAG, "str is null!");
            return;
        }

        if (cx == null){
            L.d(TAG, "The cx is null!");
            return;
        }

        if (cx instanceof Activity) {
            if (AcUtils.finishing((Activity) cx)) {
                return;
            }
        }

        to = Toast.makeText(cx, str, Toast.LENGTH_SHORT);
        to.show();
    }

    public final static void showToastNoDelay(Context cx, String str, boolean debug){
        hideToast();
        showToast(cx, str, debug);
    }

    public final static void hideToast(){
        if (to != null){
            to.cancel();
        }
        to = null;
    }
}
