package com.cily.utils.app.dia;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.cily.utils.app.*;
import com.cily.utils.app.utils.AcUtils;
import com.cily.utils.app.utils.ScreenUtils;

/**
 * user:cily
 * time:2017/6/19
 * desc:
 */

public class LoadingDialog {
    private Dialog mDialog;
    private Activity ac;
    private View rootView;

    private TextView common_dialog_msg_tv;

    public LoadingDialog(Activity ac) {
//        this(ac, R.layout.default_dia_loading);
    }

    public LoadingDialog(Activity ac, @LayoutRes int layoutId){
        if (AcUtils.finishing(ac)){
            return;
        }
        this.ac = ac;
        builder(layoutId);
    }

    private void builder(@LayoutRes int layoutId) {
//        mDialog = new Dialog(ac, R.style.CommonDialogStyle);
        Window window = mDialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = ScreenUtils.getScreenWidth(ac);
            params.height = ScreenUtils.getScreenHeight(ac);
            window.setGravity(Gravity.CENTER);
            params.x = 0;
            params.y = 0;
            window.setAttributes(params);
        }
        mDialog.setContentView(getContentView(layoutId));
    }

    @NonNull
    private View getContentView(@LayoutRes int layoutId) {
        if (layoutId <= 0){
//            layoutId = R.layout.default_dia_loading;
        }
        rootView = View.inflate(ac, layoutId, null);
        return rootView;
    }

    public LoadingDialog setDefaultMsg(String msg) {
        if (common_dialog_msg_tv == null) {
            if (rootView != null) {
//                common_dialog_msg_tv = (TextView) rootView.findViewById(R.id.tv_msg_default_dia_loading_id);
            }
        }

        if (common_dialog_msg_tv != null) {
            common_dialog_msg_tv.setText(msg);
        }
        return this;
    }

    public View getChildView(@IdRes int childViewId){
        if (rootView != null){
            return rootView.findViewById(childViewId);
        }
        return null;
    }

    public void show() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    public void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public LoadingDialog setCancelable(boolean cancel) {
        if (mDialog != null) {
            mDialog.setCancelable(cancel);
        }
        return this;
    }

    public LoadingDialog setCanceledOnTouchOutside(boolean cancel) {
        if (mDialog != null) {
            mDialog.setCanceledOnTouchOutside(cancel);
        }
        return this;
    }

    public void setOnDisListener(DialogInterface.OnDismissListener l){
        if (mDialog != null) {
            if (l != null) {
                mDialog.setOnDismissListener(l);
            }
        }
    }
}
