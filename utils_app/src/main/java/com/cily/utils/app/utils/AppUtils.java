package com.cily.utils.app.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.Uri;
import android.telephony.TelephonyManager;

import com.cily.utils.log.L;
import com.cily.utils.base.StrUtils;

import java.security.MessageDigest;

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

    public final static String getAppName(Context cx){
        PackageInfo info = getInfo(cx, 0);
        if (info == null) {
            L.v(TAG, "getAppName: info null");
            return "unkown app";
        }
        return info.packageName;

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

    /**
     * 获取当前手机系统版本号
     *
     * @return  系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return  手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限)
     *
     * @return  手机IMEI
     */
    public static String getIMEI(Context cx) {
        TelephonyManager tm = (TelephonyManager) cx.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm != null) {
            return tm.getDeviceId();
        }
        return null;
    }

    public final static String getAppSign(Context cx){
        Signature[] arrayOfSignature = getRawSignature(cx, getPackageName(cx));
        if ((arrayOfSignature == null) || (arrayOfSignature.length == 0)){
            return null;
        }

        return getMessageDigest(arrayOfSignature[0].toByteArray());
    }

    private static Signature[] getRawSignature(Context paramContext, String paramString) {
        if ((paramString == null) || (paramString.length() == 0)) {
            return null;
        }
        PackageManager localPackageManager = paramContext.getPackageManager();
        PackageInfo localPackageInfo;
        try {
            localPackageInfo = localPackageManager.getPackageInfo(paramString, PackageManager.GET_SIGNATURES);
            if (localPackageInfo == null) {
                return null;
            }
        } catch (PackageManager.NameNotFoundException localNameNotFoundException) {
            return null;
        }
        return localPackageInfo.signatures;
    }

    public static final String getMessageDigest(byte[] paramArrayOfByte) {
        char[] arrayOfChar1 = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramArrayOfByte);
            byte[] arrayOfByte = localMessageDigest.digest();
            int i = arrayOfByte.length;
            char[] arrayOfChar2 = new char[i * 2];
            int j = 0;
            int k = 0;
            while (true) {
                if (j >= i) {
                    return new String(arrayOfChar2);
                }
                int m = arrayOfByte[j];
                int n = k + 1;
                arrayOfChar2[k] = arrayOfChar1[(0xF & m >>> 4)];
                k = n + 1;
                arrayOfChar2[n] = arrayOfChar1[(m & 0xF)];
                j++;
            }
        } catch (Exception e) {
            L.printException(e);
        }
        return null;
    }

    public static final byte[] getRawDigest(byte[] paramArrayOfByte) {
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramArrayOfByte);
            byte[] arrayOfByte = localMessageDigest.digest();
            return arrayOfByte;
        } catch (Exception e) {
            L.printException(e);
        }
        return null;
    }
}