package com.cily.utils.app.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;

/**
 * user:cily
 * time:2017/2/23
 * desc:Activity工具
 */
public class AcUtils {
    private final static String TAG = "AcUtils";

    @SuppressLint("NewApi")
    public final static boolean finishing(Activity ac) {
        if (isNull(ac) || ac.isFinishing()) {
            L.v(TAG, "ac is null or isFinishing");
            return true;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (ac.isDestroyed()) {
                L.v(TAG, "ac is destroyed");
                return true;
            }
        }
        return false;
    }

    public final static boolean isNull(Activity ac) {
        return ac == null;
    }

}
