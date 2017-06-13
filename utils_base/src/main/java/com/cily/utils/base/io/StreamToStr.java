package com.cily.utils.base.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * user:cily
 * time:2017/6/13
 * desc:
 */

public class StreamToStr {
    public static String throwableToStr(Throwable e){
        if (e == null){
            return null;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(bos));
        if (bos != null){
            try {
                bos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bos.toString();
        }
        return null;
    }
}
