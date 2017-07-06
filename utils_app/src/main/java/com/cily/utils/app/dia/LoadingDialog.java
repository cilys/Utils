package com.cily.utils.app.dia;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.TextView;

import com.cily.utils.app.R;

/**
 * user:cily
 * time:2017/6/19
 * desc:
 */

public class LoadingDialog extends BaseDialog {
    private TextView common_dialog_msg_tv;

    public LoadingDialog(Activity ac) {
        this(ac, R.layout.default_dia_loading);
    }

    public LoadingDialog(Activity ac, @LayoutRes int layoutId){
        super(ac, layoutId);
    }

    public LoadingDialog setDefaultMsg(String msg) {
        if (common_dialog_msg_tv == null) {
            View v = getChildView(R.id.tv_msg_default_dia_loading_id);
            if (v != null && v instanceof TextView) {
                common_dialog_msg_tv = (TextView) v;
            }
        }

        if (common_dialog_msg_tv != null) {
            common_dialog_msg_tv.setText(msg);
        }
        return this;
    }
}
