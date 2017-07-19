package com.cily.utils.app.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;

import com.cily.utils.log.L;
import com.cily.utils.base.StrUtils;

/**
 * user:cily
 * time:2017/2/23
 * desc:App工具
 */
public class AppUtils {
    private final static String TAG = "AppUtils";

    @SuppressLint("NewApi")
    public final static String getMetaData(Context cx, String metaKey) {
        if (metaKey == null) {
            L.v(TAG, "getMetaData: metaKey null");
            return null;
        }

        PackageManager pm = getPm(cx);
        if (pm == null) {
            L.v(TAG, "getMetaData: pm null");
            return null;
        }
        try {
            ApplicationInfo info = pm.getApplicationInfo(cx.getPackageName(), PackageManager.GET_META_DATA);
            return info.metaData.getString(metaKey, null);
        } catch (NameNotFoundException e) {
            L.printException(e);
            return null;
        }
    }

    public final static int getVersionCode(Context cx) {
        PackageInfo info = getInfo(cx, 0);
        if (info == null) {
            L.v(TAG, "getVersionCode: info null");
            return 1;
        }

        return info.versionCode;
    }

    public final static String getPackageName(Context cx){
        return cx.getPackageName();
    }

    public final static String getVersionName(Context cx) {
        PackageInfo info = getInfo(cx, 0);
        if (info == null) {
            L.v(TAG, "getVersionName: info null");
            return "1.0.0";
        }
        return info.versionName;
    }

    private final static PackageManager getPm(Context cx) {
        if (cx == null) {
            L.v(TAG, "getPm: cx null");
            return null;
        }

        return cx.getPackageManager();
    }

    private final static PackageInfo getInfo(Context cx, int flags) {
        PackageManager pm = getPm(cx);
        if (pm == null) {
            L.v(TAG, "getInfo: pm null");
            return null;
        }

        try {
            return pm.getPackageInfo(cx.getPackageName(), flags);
        } catch (NameNotFoundException e) {
            L.printException(e);
        }
        return null;
    }

    public final static void exitApp() {
        System.exit(0);
    }

    public final static void toPhone(Context cx, String phoneNum){
        if (StrUtils.isEmpty(phoneNum)){
            L.d(TAG, "The phoneNum is empty!");
            return;
        }

        Intent i = new Intent();
        i.setAction(Intent.ACTION_CALL);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setData(Uri.parse("tel:" + phoneNum));
        cx.startActivity(i);
    }
}