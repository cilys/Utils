package com.cily.utils.app.rx;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * user:cily
 * time:2017/2/20
 * desc:
 */

public class ActivityRxEvent {

    public final static int CREATE = 1;
    public final static int START = 2;
    public final static int RESTART = 3;
    public final static int RESUME = 4;
    public final static int PAUSE = 5;
    public final static int STOP = 6;
    public final static int DESTROY = 7;

    @IntDef({CREATE, START, RESTART, RESUME, PAUSE, STOP, DESTROY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Rx{}
}
