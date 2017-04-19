package com.cily.utils.app.fg;

import android.support.v4.app.Fragment;

import com.cily.utils.app.Init;
import com.cily.utils.app.utils.ToastUtils;


/**
 * user:cily
 * time:2017/1/16
 * desc:
 */

public class BaseFragment extends Fragment {
    protected final String TAG = this.getClass().getSimpleName();

    protected void showToast(String str) {
        ToastUtils.showToast(getActivity(), str, Init.isShowToast());
    }

    protected void debugToast(String str) {
        ToastUtils.showToast(getActivity(), str, Init.isDebug());
    }
}