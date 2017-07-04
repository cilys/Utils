package com.cily.utils.app.rx.okhttp;

import com.cily.utils.app.rx.ac.BaseOkHttpActivity;
import com.cily.utils.app.rx.bean.AppVersionBean;
import com.cily.utils.app.utils.AcUtils;
import com.cily.utils.app.utils.log.L;
import com.trello.rxlifecycle.LifecycleProvider;

import java.util.Map;

/**
 * user:cily
 * time:2017/7/4
 * desc:
 */

public class ApkUploadUtils {
    private final static String TAG = ApkUploadUtils.class.getSimpleName();

    public void check(BaseOkHttpActivity ac, String url, Map<String, String>header,
                      Map<String, String> map_param, ResultSubscriber<AppVersionBean> rs){
        if (AcUtils.finishing(ac)){
            return;
        }

        if (rs == null) {
            NetWork.get(ac, url, header, map_param, new ResultSubscriber<AppVersionBean>() {
                @Override
                public void onSuccess(AppVersionBean bean) {
                    if (bean != null) {

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
            NetWork.get(ac, url, header, map_param, rs);
        }
    }

    public void downloadApk(){

    }
}