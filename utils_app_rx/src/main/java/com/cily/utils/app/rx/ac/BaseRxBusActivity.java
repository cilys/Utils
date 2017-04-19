package com.cily.utils.app.rx.ac;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cily.utils.app.ac.BaseActivity;
import com.cily.utils.app.event.Event;
import com.cily.utils.app.rx.ActivityRxEvent;
import com.cily.utils.app.rx.RxBus;

import rx.Subscription;
import rx.functions.Action1;

/**
 * user:cily
 * time:2017/1/20
 * desc:RxBus
 */

public class BaseRxBusActivity extends BaseActivity {
    private Subscription mSubscription;
    private boolean resetEvent = true;

    private int unSub = ActivityRxEvent.DESTROY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRxBus();
        if (getUnSub() == ActivityRxEvent.CREATE){
            unSubscription();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (getUnSub() == ActivityRxEvent.START){
            unSubscription();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if (getUnSub() == ActivityRxEvent.RESTART){
            unSubscription();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (getUnSub() == ActivityRxEvent.RESUME){
            unSubscription();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (getUnSub() == ActivityRxEvent.PAUSE){
            unSubscription();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (getUnSub() == ActivityRxEvent.STOP){
            unSubscription();
        }
    }

    @Override
    protected void onDestroy() {
        if (getUnSub() == ActivityRxEvent.DESTROY){
            unSubscription();
        }

        super.onDestroy();
    }

    private void unSubscription(){
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mSubscription = null;
    }

    private void initRxBus(){
        if (mSubscription == null) {
            mSubscription = RxBus.getInstance()
                    .toObservable(Event.class)
                    .subscribe(new Action1<Event>() {
                        @Override
                        public void call(Event event) {
                            if (event == null){
                                return;
                            }

                            if (event.what == Event.APP_EXIT){
                                System.exit(0);
                                return;
                            }

                            doRxbus(event);

                            if (isResetEvent()){
                                event.recycle();
                            }
                        }
                    });
        }
    }

    protected void doRxbus(Event e){}

    private boolean isResetEvent() {
        return resetEvent;
    }

    protected void setResetEvent(boolean reset) {
        this.resetEvent = reset;
    }

    protected int getUnSub() {
        return unSub;
    }

    protected void setUnSub(@ActivityRxEvent.Rx int unSub) {
        this.unSub = unSub;
    }
}
