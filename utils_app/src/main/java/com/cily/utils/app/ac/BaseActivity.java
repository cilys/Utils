package com.cily.utils.app.ac;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cily.utils.app.Init;
import com.cily.utils.app.dia.LoadingDialog;
import com.cily.utils.app.listener.LoadingDialogCountDownTimerListener;
import com.cily.utils.app.utils.ToastUtils;
import com.cily.utils.base.StrUtils;
import com.cily.utils.log.L;


/**
 * user:cily
 * time:2017/1/16
 * desc:
 */
public class BaseActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();

    protected void showToast(String str) {
        ToastUtils.showToast(this, str, Init.isShowToast());
    }

    protected void hideToast(){
        ToastUtils.hideToast();
    }

    protected void showToastNoDelay(String str){
        ToastUtils.showToastNoDelay(this, str, Init.isShowToast());
    }

    protected <V extends View> V findView(@IdRes int id){
        try{
            return (V)findViewById(id);
        }catch (ClassCastException e){
            L.e(TAG, "Could not cast View to concrete class");
            return null;
        }
    }

    protected Button findBtn(@IdRes int id){
        return findView(id);
    }

    protected TextView findTv(@IdRes int id){
        return findView(id);
    }

    protected ImageView findImg(@IdRes int id){
        return findView(id);
    }

    protected void debugToast(String str) {
        ToastUtils.showToast(this, str, Init.isDebug());
    }

    protected void toAc(Class<? extends Activity> c, Bundle b){
        if (c != null){
            Intent i = new Intent(this, c);

            if (b != null){
                i.putExtras(b);
            }

            startActivity(i);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        disLoading();
    }

    protected void toAcWithFinish(Class<? extends Activity> c, Bundle b){
        toAc(c, b);
        finish();
    }

    private LoadingDialog loadingDia;
    protected void showLoading(){
        showLoading(null);
    }
    protected void showLoading(String msg){
        if (loadingDia == null) {
            loadingDia = new LoadingDialog(this);
            if (loadingDia == null){
                return;
            }

            if (!StrUtils.isEmpty(msg)){
                loadingDia.setDefaultMsg(msg);
            }
        }
        startLoadingTimer(loadingCountDownTimeTotal);

        loadingDia.show();
    }

    protected LoadingDialog getLoadingDialog(@LayoutRes int layoutId){
        return new LoadingDialog(this, layoutId);
    }

    protected void disLoading(){
        if (loadingDia != null){
            loadingDia.dismiss();
        }
        stopLoadingTimer();
    }

    protected void setOnLoadingDismiss(DialogInterface.OnDismissListener l){
        if (l != null){
            if (loadingDia != null){
                loadingDia.setOnDisListener(l);
            }
        }
    }

    public final static long DEFAULT_LOADING_TOTAL_TIMER = 120000;
    public void setLoadingCountDownTimeInterval(long loadingCountDownTimeInterval) {
        this.loadingCountDownTimeInterval = loadingCountDownTimeInterval;
    }

    private long loadingCountDownTimeInterval = 1000;
    private long loadingCountDownTimeTotal = DEFAULT_LOADING_TOTAL_TIMER;

    private CountDownTimer loadingTimer;
    protected void startLoadingTimer(long loadingTotalTimer){
        startLoadingTimer(loadingTotalTimer, null);
    }

    protected void startLoadingTimer(long loadingTotalTimer, final LoadingDialogCountDownTimerListener listener){
        if (loadingTimer == null){
            loadingTimer = new CountDownTimer(loadingTotalTimer, loadingCountDownTimeInterval) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    if (listener != null){
                        listener.onFinish();
                    }else {
                        showToast("超时");
                    }
                    disLoading();
                }
            };
        }
        stopLoadingTimer();
        loadingTimer.start();
    }

    protected void stopLoadingTimer(){
        if (loadingTimer != null){
            loadingTimer.cancel();
        }
    }

    public void setLoadingCountDownTimeTotal(long loadingCountDownTimeTotal) {
        this.loadingCountDownTimeTotal = loadingCountDownTimeTotal;
    }
}
