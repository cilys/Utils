package com.cily.utils.app.rx.fg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cily.utils.app.event.Event;
import com.cily.utils.app.fg.BaseFragment;
import com.cily.utils.app.rx.FragmentRxEvent;
import com.cily.utils.app.rx.RxBus;
import com.cily.utils.log.L;

import rx.Subscription;
import rx.functions.Action1;

/**
 * user:xuli
 * time:2017/2/20
 * desc: rxbus
 */

public class BaseRxBusFragment extends BaseFragment {
    private Subscription mSubscription;
    private boolean resetEvent = true;
    private int unSub = FragmentRxEvent.DESTROY;

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

    protected void doRxbus(Event e){

    }

    private void unSubscription(){
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mSubscription = null;
    }

    private boolean isResetEvent() {
        return resetEvent;
    }

    protected void setResetEvent(boolean reset) {
        this.resetEvent = reset;
    }

    protected int getUnSub() {
        return unSub;
    }

    protected void setUnSub(@FragmentRxEvent.Rx int unSub) {
        this.unSub = unSub;
    }

    @Override
    public void onAttach(android.app.Activity activity) {
        super.onAttach(activity);
        L.i(TAG, "<--->onAttach");
        initRxBus();

        if (getUnSub() == FragmentRxEvent.ATTACH){
            unSubscription();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.i(TAG, "<--->onCreate");
        if (getUnSub() == FragmentRxEvent.CREATE){
            unSubscription();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        L.i(TAG, "<--->onViewCreated");
        if (getUnSub() == FragmentRxEvent.CREATE_VIEW){
            unSubscription();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        L.i(TAG, "<--->onStart");
        if (getUnSub() == FragmentRxEvent.START){
            unSubscription();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        L.i(TAG, "<--->onResume");
        if (getUnSub() == FragmentRxEvent.RESUME){
            unSubscription();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        L.i(TAG, "<--->onPause");
        if (getUnSub() == FragmentRxEvent.PAUSE){
            unSubscription();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        L.i(TAG, "<--->onStop");
        if (getUnSub() == FragmentRxEvent.STOP){
            unSubscription();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        L.i(TAG, "<--->onDestroyView");

        if (getUnSub() == FragmentRxEvent.DESTROY_VIEW){
            unSubscription();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.i(TAG, "<--->onDestroy");

        if (getUnSub() == FragmentRxEvent.DESTROY){
            unSubscription();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        L.i(TAG, "<--->onDetach");

        if (getUnSub() == FragmentRxEvent.DETACH){
            unSubscription();
        }
    }
}