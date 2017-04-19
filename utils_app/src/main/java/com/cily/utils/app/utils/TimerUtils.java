package com.cily.utils.app.utils;

import android.os.Handler;
import android.os.Message;

/**
 * user:cily
 * time:2017/1/19
 * desc:
 */

public abstract class TimerUtils {
    private boolean mCancelled = false;
    private final long timeInterval;
    private long num = 0;

    public TimerUtils(long timeInterval) {
        mCancelled = true;
        this.timeInterval = timeInterval;
    }

    public synchronized final void cancel() {
        mCancelled = true;
        mHandler.removeMessages(MSG);
    }

    public synchronized final void start() {
        if (mCancelled) {
            mHandler.sendEmptyMessageDelayed(MSG, timeInterval);
        }
        mCancelled = false;
    }

    public final void reset() {
        if (mCancelled) {
            num = 0;
        }
    }

    public abstract void onTick(long time);

    private static final int MSG = 1;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MSG) {
                synchronized (TimerUtils.this) {
                    if (mCancelled) {
                        return;
                    }
                    num++;
                    onTick(num * timeInterval);
                    sendEmptyMessageDelayed(MSG, timeInterval);
                }
            }
        }
    };
}