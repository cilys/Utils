package com.cily.utils.app.dia;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cily.utils.app.R;

/**
 * user:cily
 * time:2017/7/4
 * desc:
 */

public class DownloadProgressDialog extends BaseDialog {
    private TextView tv_bottom, tv_pro;
    private ProgressBar proBar;

    public DownloadProgressDialog(Activity ac) {
        this(ac, R.layout.default_dia_download_progress);
    }

    public DownloadProgressDialog(Activity ac, @LayoutRes int layoutId){
        super(ac, layoutId);
    }

    public void setBtnMsg(String msg, View.OnClickListener listener){
        if (msg == null){
            return;
        }

        if (tv_bottom == null){
            View v = getChildView(R.id.tv_bottom);
            if (v != null && v instanceof TextView) {
                tv_bottom = (TextView) v;
            }
        }

        if (tv_bottom != null){
            tv_bottom.setText(msg);

            if (listener != null){
                tv_bottom.setOnClickListener(listener);
            }else{
                tv_bottom.setVisibility(View.GONE);
            }
        }
    }

    public void showProgress(String pro){
        if (pro == null){
            return;
        }
        if (tv_pro == null){
            View v = getChildView(R.id.tv_pro);
            if (v != null && v instanceof TextView) {
                tv_pro = (TextView) v;
            }
        }
        if (tv_pro != null){
            tv_pro.setText(pro);
        }
    }

    public void setProgress(int progress){
        if (progress < 0){
            progress = 0;
        }
        if (progress > 100){
            progress = 100;
        }

        if (proBar == null){
            View v = getChildView(R.id.proBar);
            if (v != null && v instanceof ProgressBar){
                proBar = (ProgressBar)v;
            }
        }
        if (proBar != null){
            proBar.setProgress(progress);
        }
    }
}
