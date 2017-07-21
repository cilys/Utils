package com.cily.utils.app.rx.okhttp;

import android.app.Activity;
import android.view.View;

import com.cily.utils.app.dia.DoubleButtonDialog;
import com.cily.utils.app.rx.R;
import com.cily.utils.app.rx.ac.BaseOkHttpActivity;
import com.cily.utils.app.rx.bean.AppVersionBean;
import com.cily.utils.app.utils.AcUtils;
import com.cily.utils.app.utils.AppUtils;
import com.cily.utils.log.L;
import com.cily.utils.base.StrUtils;

import java.util.Map;

/**
 * user:cily
 * time:2017/7/4
 * desc:
 */

public class ApkUploadUtils {
    private final static String TAG = ApkUploadUtils.class.getSimpleName();

    public void check(final BaseOkHttpActivity ac, String url, Map<String, String>header,
                      Map<String, String> map_param, ResultSubscriber<AppVersionBean> rs){
        if (AcUtils.finishing(ac)){
            return;
        }

        if (rs == null) {
            NetWork.getAppUpdate(ac, url, header, map_param, new ResultSubscriber<AppVersionBean>() {
                @Override
                public void onSuccess(AppVersionBean bean) {
                    if (bean != null) {
                        if (bean.getStatus() == AppVersionBean.STATUS_NONE){

                        }else if (bean.getStatus() == AppVersionBean.STATUS_NORMAL){

                        }else if (bean.getStatus() == AppVersionBean.STATUS_FORCE){
                            showForceUpdateButton(ac, bean.getUpdateTitle(), bean.getUpdateMsg(),
                                    bean.getLeftBtnMsg(), bean.getRightBtnMsg(), bean.getApkUrl());
                        }
                    } else {
                        L.i(TAG, "The AppVersionBean is null!");
                    }
                }

                @Override
                public void onFailure(String msg) {
                    L.w(TAG, "check onFailure = " + msg);
                }
            });
        }else{
            NetWork.getAppUpdate(ac, url, header, map_param, rs);
        }
    }

    public void downloadApk(Activity ac, String url){

    }

    private void showForceUpdateButton(final Activity ac, String title, String msg, String leftBtnMsg,
                                       String rightBtnMsg, final String url){
        if (AcUtils.finishing(ac)){
            return;
        }
        final DoubleButtonDialog dia = new DoubleButtonDialog(ac);
        dia.setTitle(StrUtils.isEmpty(title) ?
                AcUtils.getString(ac, R.string.str_default_app_update_title) : title);

        dia.setMsg(StrUtils.isEmpty(msg) ?
                AcUtils.getString(ac, R.string.str_default_app_update_msg) : msg);
        dia.setLeftBtnMsg(StrUtils.isEmpty(leftBtnMsg) ?
                        AcUtils.getString(ac, R.string.str_default_app_update_force) : leftBtnMsg,

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        L.v(TAG, "force update dialog left button be clicked!!!");
                        dia.dismiss();
                    }
                });

        dia.setRightBtnMsg(StrUtils.isEmpty(rightBtnMsg) ?
                        AcUtils.getString(ac, R.string.str_default_app_update_force) : rightBtnMsg,

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        L.v(TAG, "force update dialog right button be clicked!!!");
                        dia.dismiss();
                        AppUtils.exitApp();
                    }
                });
    }
}