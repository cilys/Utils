package com.cily.utils.app.ac;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cily.utils.app.Init;
import com.cily.utils.app.utils.log.L;
import com.cily.utils.app.utils.ToastUtils;


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

    protected void toAcWithFinish(Class<? extends Activity> c, Bundle b){
        toAc(c, b);
        finish();
    }
}
