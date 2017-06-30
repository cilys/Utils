package com.cily.utils.app.fg;

import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;

import com.cily.utils.app.Init;
import com.cily.utils.app.ac.BaseActivity;
import com.cily.utils.app.dia.LoadingDialog;
import com.cily.utils.app.listener.LoadingDialogCountDownTimerListener;
import com.cily.utils.app.utils.ToastUtils;
import com.cily.utils.base.StrUtils;


/**
 * user:cily
 * time:2017/1/16
 * desc:
 */

public class BaseFragment extends Fragment {
    protected final String TAG = this.getClass().getSimpleName();

    protected void showToast(String str) {
        ToastUtils.showToast(getActivity(), str, Init.isShowToast());
    }

    protected void debugToast(String str) {
        ToastUtils.showToast(getActivity(), str, Init.isDebug());
    }

    private LoadingDialog loadingDia;
    protected void showLoading(){
        showLoading(null);
    }
    protected void showLoading(String msg){
        if (loadingDia == null) {
            loadingDia = new LoadingDialog(getActivity());
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
        return new LoadingDialog(getActivity(), layoutId);
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

    public void setLoadingCountDownTimeInterval(long loadingCountDownTimeInterval) {
        this.loadingCountDownTimeInterval = loadingCountDownTimeInterval;
    }

    private long loadingCountDownTimeInterval = 1000;
    private long loadingCountDownTimeTotal = BaseActivity.DEFAULT_LOADING_TOTAL_TIMER;

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