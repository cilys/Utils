package com.cily.utils.app.listener;

import android.view.View;

/**
 * user:cily
 * time:2017/8/16
 * desc:
 */

public abstract class SingleClickListener implements View.OnClickListener{
    private final long DEFAULT_TIME_INVAL = 500;
    private long time_inval = 0;
    private long time_last_click;

    public SingleClickListener() {
        time_inval = DEFAULT_TIME_INVAL;
    }

    public SingleClickListener(long time) {
        if (time <= 0){
            time = 0;
        }
        this.time_inval = time;
    }

    @Override
    public final void onClick(View v) {
        if (time_inval <= 0){
            onSingleClick(v);
        }else {
            if (System.currentTimeMillis() - time_last_click >= time_inval){
                time_last_click = System.currentTimeMillis();
                onSingleClick(v);
            }
        }
    }

    public abstract void onSingleClick(View v);
}
