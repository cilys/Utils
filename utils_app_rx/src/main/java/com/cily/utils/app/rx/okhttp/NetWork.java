package com.cily.utils.app.rx.okhttp;

import com.trello.rxlifecycle.LifecycleProvider;

import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * user:cily
 * time:2017/2/20
 * desc:需自行实现
 */

public class NetWork {


//    private static NetService getService(){
//        return RetrofitUtils.getInstance(false).getRetrofit().create(NetService.class);
//    }
//
//    public static <T> void get(LifecycleProvider lp, String url, Map<String, String>map_header,
//                               Map<String, String>map, ResultSubscriber<T> rs){
//
//        Observable o = getService().get(url, map_header, map)
//                .map(new BaseEntity()).compose(lp.bindToLifecycle());
//
//        toSubscribe(o, rs);
//    }
//
//    public static <T>void post(LifecycleProvider lp, String url, Map<String, String>map_header,
//                               Map<String, String>map, ResultSubscriber<T> rs){
//        Observable o = getService().post(url, map_header, map)
//                .map(new BaseEntity()).compose(lp.bindToLifecycle());
//
//        toSubscribe(o, rs);
//    }
//
//    public static <T>void postForm(LifecycleProvider lp, String url, Map<String, String>map_header,
//                                   Map<String, String>map, ResultSubscriber<T> rs){
//        Observable o = getService().postForm(url, map_header, map, null)
//                .map(new BaseEntity()).compose(lp.bindToLifecycle());
//
//        toSubscribe(o, rs);
//    }
//
//    private static <T> void toSubscribe(Observable<T> observable, Observer<T> observer) {
//        observable.subscribeOn(Schedulers.io())
//                .subscribeOn(Schedulers.newThread())//子线程访问网络
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
//                .subscribe(observer);
//    }
}
