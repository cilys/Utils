package com.cily.utils.app.rx;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * user:cily
 * time:2017/2/20
 * desc:
 */

public class FragmentRxEvent {
    public final static int ATTACH = 1;
    public final static int CREATE = 2;
    public final static int CREATE_VIEW = 3;
    public final static int START = 4;
    public final static int RESUME = 5;
    public final static int PAUSE = 6;
    public final static int STOP = 7;
    public final static int DESTROY_VIEW = 8;
    public final static int DESTROY = 9;
    public final static int DETACH = 10;

    @IntDef({ATTACH, CREATE, CREATE_VIEW, START, RESUME, PAUSE, STOP, DESTROY_VIEW, DESTROY, DETACH})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Rx{}
}
