package com.cily.utils.all;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//import com.cily.utils.app.ac.BaseActivity;


public class SplashAc extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_splash);

//        showLoading();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                toAc(NavAc.class, null);
//            }
//        }, 3000);
    }
}
