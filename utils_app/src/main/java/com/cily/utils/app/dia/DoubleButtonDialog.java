package com.cily.utils.app.dia;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cily.utils.app.R;

/**
 * user:cily
 * time:2017/7/4
 * desc:
 */

public class DoubleButtonDialog extends BaseDialog {
    private TextView tv_bottom_left, tv_bottom_right;
    private EditText ed_msg;

    public DoubleButtonDialog(Activity ac) {
        super(ac, R.layout.default_dia_doublebtn);
    }

    public DoubleButtonDialog(Activity ac, @LayoutRes int layoutId){
        super(ac, layoutId);
    }

    public void setMsg(String msg){
        if (msg == null){
            return;
        }

        if (ed_msg == null){
            View v = getChildView(R.id.ed_msg);
            if (v != null && v instanceof EditText) {
                ed_msg = (EditText) v;
            }
        }

        if (ed_msg != null){
            ed_msg.setText(msg);
        }
    }

    public void setLeftBtnMsg(String msg, View.OnClickListener listener){
        if (msg == null){
            return;
        }

        if (tv_bottom_left == null){
            View v = getChildView(R.id.tv_bottom_left);
            if (v != null && v instanceof TextView) {
                tv_bottom_left = (TextView) v;
            }
        }

        if (tv_bottom_left != null){
            tv_bottom_left.setText(msg);

            if (listener != null){
                tv_bottom_left.setOnClickListener(listener);
            }
        }
    }

    public void setRightBtnMsg(String msg, View.OnClickListener listener){
        if (msg == null){
            return;
        }

        if (tv_bottom_right == null){
            View v = getChildView(R.id.tv_bottom_right);
            if (v != null && v instanceof TextView) {
                tv_bottom_right = (TextView) v;
            }
        }

        if (tv_bottom_right != null){
            tv_bottom_right.setText(msg);

            if (listener != null){
                tv_bottom_right.setOnClickListener(listener);
            }
        }
    }
}
