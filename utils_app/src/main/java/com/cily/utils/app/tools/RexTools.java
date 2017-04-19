package com.cily.utils.app.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RexTools {

    /**
     * 是否是手机号
     * @param str
     * @return
     */
    public boolean isMobile(String str) {
        Pattern pattern = Pattern.compile("1[0-9]{10}");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
