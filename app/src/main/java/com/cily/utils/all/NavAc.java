package com.cily.utils.all;

import android.os.Bundle;
import android.view.View;

import com.cily.utils.app.Init;
import com.cily.utils.app.ac.BaseActivity;
import com.cily.utils.app.sql.DbUtils;
import com.cily.utils.app.utils.SDCardUtils;
import com.cily.utils.app.utils.log.L;

import java.io.File;

public class NavAc extends BaseActivity {
    private final int MAX = 10;

    int num = 0;
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_nav);

        Init.setWriteLog(true, SDCardUtils.getInnerSDCardPath() + File.separator + "logs", null);
        DbUtils.init(this, true);

        findView(R.id.btn_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = Integer.valueOf("a");
            }
        });

        new Thread("线程1："){
            @Override
            public void run() {
                while (num < MAX){
                    num ++;
                    L.v(TAG, Thread.currentThread().getName() + "测试v：" + num);
                    L.d(TAG, Thread.currentThread().getName() + "测试d：" + num);
                    L.i(TAG, Thread.currentThread().getName() + "测试i：" + num);
                    L.w(TAG, Thread.currentThread().getName() + "测试w：" + num);
                    L.e(TAG, Thread.currentThread().getName() + "测试e：" + num);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        L.printException(e);
                    }
                }
            }
        }.start();

        new Thread("线程2："){
            @Override
            public void run() {
                while (num < MAX){
                    num ++;
                    L.v(TAG, Thread.currentThread().getName() + "测试v：" + num);
                    L.d(TAG, Thread.currentThread().getName() + "测试d：" + num);
                    L.i(TAG, Thread.currentThread().getName() + "测试i：" + num);
                    L.w(TAG, Thread.currentThread().getName() + "测试w：" + num);
                    L.e(TAG, Thread.currentThread().getName() + "测试e：" + num);

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        L.printException(e);
                    }
                }
            }
        }.start();

        new Thread("线程3："){
            @Override
            public void run() {
                while (num < MAX){
                    num ++;
                    L.v(TAG, Thread.currentThread().getName() + "测试v：" + num);
                    L.d(TAG, Thread.currentThread().getName() + "测试d：" + num);
                    L.i(TAG, Thread.currentThread().getName() + "测试i：" + num);
                    L.w(TAG, Thread.currentThread().getName() + "测试w：" + num);
                    L.e(TAG, Thread.currentThread().getName() + "测试e：" + num);

                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        L.printException(e);
                    }
                }
            }
        }.start();

        new Thread("线程4："){
            @Override
            public void run() {
                while (num < MAX){
                    num ++;
                    L.v(TAG, Thread.currentThread().getName() + "测试v：" + num);
                    L.d(TAG, Thread.currentThread().getName() + "测试d：" + num);
                    L.i(TAG, Thread.currentThread().getName() + "测试i：" + num);
                    L.w(TAG, Thread.currentThread().getName() + "测试w：" + num);
                    L.e(TAG, Thread.currentThread().getName() + "测试e：" + num);

                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        L.printException(e);
                    }
                }
            }
        }.start();

        new Thread("线程5："){
            @Override
            public void run() {
                while (num < MAX){
                    num ++;
                    L.v(TAG, Thread.currentThread().getName() + "测试v：" + num);
                    L.d(TAG, Thread.currentThread().getName() + "测试d：" + num);
                    L.i(TAG, Thread.currentThread().getName() + "测试i：" + num);
                    L.w(TAG, Thread.currentThread().getName() + "测试w：" + num);
                    L.e(TAG, Thread.currentThread().getName() + "测试e：" + num);





                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        L.printException(e);
                    }
                }
            }
        }.start();
    }
}
