package com.cily.utils.app.dia;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.cily.utils.app.R;
import com.cily.utils.app.utils.AcUtils;
import com.cily.utils.app.utils.ScreenUtils;

/**
 * user:cily
 * time:2017/7/4
 * desc:
 */

public class BaseDialog {
    protected final String TAG = this.getClass().getSimpleName();
    private Dialog mDialog;
    private Activity ac;
    private View rootView;
    private TextView tv_title;

    public BaseDialog(Activity ac, @LayoutRes int layoutId){
        if (AcUtils.finishing(ac)){
            return;
        }

        this.ac = ac;
        builder(layoutId);
    }

    private void builder(@LayoutRes int layoutId) {
        mDialog = new Dialog(ac, R.style.CommonDialogStyle);
        Window window = mDialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = (int)(ScreenUtils.getScreenWidth(ac) * 0.8);
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
            layoutId = R.layout.default_dia_singlebtn;
        }
        rootView = View.inflate(ac, layoutId, null);
        return rootView;
    }

    public void setTitle(String msg){
        if (msg == null){
            return;
        }

        if (tv_title == null){
            if (rootView != null){
                tv_title = (TextView)rootView.findViewById(R.id.tv_title);
            }
        }
        if (tv_title != null){
            tv_title.setText(msg);
        }
    }

    public void show() {
        if (!AcUtils.finishing(ac) && mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    public void dismiss() {
        if (!AcUtils.finishing(ac) && mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public BaseDialog setCancelable(boolean cancel) {
        if (!AcUtils.finishing(ac) && mDialog != null) {
            mDialog.setCancelable(cancel);
        }
        return this;
    }

    public BaseDialog setCanceledOnTouchOutside(boolean cancel) {
        if (!AcUtils.finishing(ac) && mDialog != null) {
            mDialog.setCanceledOnTouchOutside(cancel);
        }
        return this;
    }

    public void setOnDisListener(DialogInterface.OnDismissListener l){
        if (!AcUtils.finishing(ac) && mDialog != null) {
            if (l != null) {
                mDialog.setOnDismissListener(l);
            }
        }
    }

    public View getChildView(@IdRes int childViewId){
        if (rootView != null){
            return rootView.findViewById(childViewId);
        }
        return null;
    }
}
