package com.cily.utils.secret;

import java.math.BigInteger;
import java.security.MessageDigest;

import com.cily.utils.base.log.Logs;
import com.cily.utils.base.StrUtils;

public class MD5Utils {
	private final static String TAG = "MD5Utils";
	
	private static byte[] encryptMD5(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data);
        return md5.digest();
    }

    public static String getMD5Str(String str) {
        if (str == null || str.trim().length() == 0) {
            Logs.sysOut(StrUtils.join(TAG, ":", "getMD5Str: str is empty"));
            return null;
        }
        BigInteger md5Data = null;
        try {
            md5Data = new BigInteger(1, encryptMD5(str.trim().getBytes()));
        } catch (Exception e) {
            Logs.printException(e);
        }
        if (md5Data == null) {
            Logs.sysOut(StrUtils.join(TAG, ":", "getMD5Str: md5Data is empty"));
            return null;
        }
        String md5Str = md5Data.toString(16);
        if (md5Str.length() < 32) {
            md5Str += 0;
        }
        return md5Str;
    }
}
