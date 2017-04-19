package com.cily.utils.app.rx;


import com.cily.utils.app.event.Event;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * user:cily
 * time:2017/1/20
 * desc:
 */

public class RxBus {
    private static volatile RxBus mRxbus;
    private final Subject<Object, Object> bus;

    private RxBus(){
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    public static RxBus getInstance(){
        if (mRxbus == null){
            synchronized(RxBus.class){
                if (mRxbus == null){
                    mRxbus = new RxBus();
                }
            }
        }
        return mRxbus;
    }

    public void post(Event e){
        if (bus != null){
            if (bus.hasObservers()) {
                bus.onNext(e);
            }
        }
    }

    public <T> Observable<T> toObservable(Class<T> eventType){
        if (bus != null){
            return bus.ofType(eventType);
        }else {
            return null;
        }
    }
}
