package com.cily.utils.app.rx.okhttp;

import android.app.Activity;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import com.cily.utils.app.ac.BaseActivity;
import com.cily.utils.app.dia.DoubleButtonDialog;
import com.cily.utils.app.dia.DownloadProgressDialog;
import com.cily.utils.app.rx.R;
import com.cily.utils.app.rx.ac.BaseOkHttpActivity;
import com.cily.utils.app.rx.bean.AppVersionBean;
import com.cily.utils.app.utils.AcUtils;
import com.cily.utils.app.utils.AppUtils;
import com.cily.utils.app.utils.SDCardUtils;
import com.cily.utils.app.utils.ToastUtils;
import com.cily.utils.base.HttpUtils;
import com.cily.utils.log.L;
import com.cily.utils.base.StrUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * user:cily
 * time:2017/7/4
 * desc:
 */

public class ApkUploadUtils {
    private final String TAG = getClass().getSimpleName();

    public void check(String url, Map<String, String>header,
                      Map<String, String> map_param, ResultSubscriber<AppVersionBean> rs){
        if (AcUtils.finishing(ac)){
            return;
        }

        if (rs == null) {
            NetWork.getAppUpdate(ac, url, header, map_param, new ResultSubscriber<AppVersionBean>() {
                @Override
                public void onSuccess(AppVersionBean bean) {
                    if (bean != null) {
                        if (bean.getStatus() == AppVersionBean.STATUS_NORMAL){
                            showUpdateButton(bean.getUpdateTitle(), bean.getUpdateMsg(),
                                    bean.getLeftBtnMsg(), bean.getRightBtnMsg(), bean.getApkUrl());

                        }else if (bean.getStatus() == AppVersionBean.STATUS_FORCE){
                            apkUrl = bean.getApkUrl();
                            showForceUpdateButton(bean.getUpdateTitle(), bean.getUpdateMsg(),
                                    bean.getLeftBtnMsg(), bean.getRightBtnMsg(), bean.getApkUrl());
                        }
                    } else {
                        L.i(TAG, "The AppVersionBean is null!");
                    }
                    if (ac != null) {
                        ac.disLoading();
                    }
                }

                @Override
                public void onFailure(String errorCode, String msg) {
                    L.w(TAG, "check onFailure = " + msg);
                    if (ac != null) {
                        ac.disLoading();
                    }
                }
            });
        }else{
            NetWork.getAppUpdate(ac, url, header, map_param, rs);
        }
    }

    private BaseOkHttpActivity ac;
    private String fileUrl;
    private String apkUrl;
    public ApkUploadUtils(BaseOkHttpActivity ac){
        if(ac == null){
            return;
        }
        this.ac = ac;
        fileUrl = ac.getPackageCodePath() + File.separator + "files" + File.separator + "app.apk";
        L.d(TAG, "ApkUploadUtils fileUrl = " + fileUrl);
    }

    private DoubleButtonDialog dia;
    private void showForceUpdateButton(String title, String msg, String leftBtnMsg,
                                       String rightBtnMsg, final String url){
        if (AcUtils.finishing(ac)){
            return;
        }
        if (dia == null) {
            dia = new DoubleButtonDialog(ac);
            dia.setCanceledOnTouchOutside(false);
            dia.setCanceledOnTouchOutside(false);
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
                            showDownloadingDialog(true);
                            downloadFile(handler, url, fileUrl, true);
                        }
                    });

            dia.setRightBtnMsg(StrUtils.isEmpty(rightBtnMsg) ?
                            AcUtils.getString(ac, R.string.str_exit) : rightBtnMsg,

                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            L.v(TAG, "force update dialog right button be clicked!!!");
                            dia.dismiss();
                            AppUtils.exitApp();
                        }
                    });
        }
        dia.show();
    }

    private void showUpdateButton(String title, String msg, String leftBtnMsg,
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
                        AcUtils.getString(ac, R.string.str_default_app_update_normal) : leftBtnMsg,

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        L.v(TAG, "normal update dialog left button be clicked!!!");
                        dia.dismiss();
                        showDownloadingDialog(false);
                        downloadFile(handler, url, fileUrl, false);
                    }
                });

        dia.setRightBtnMsg(StrUtils.isEmpty(rightBtnMsg) ?
                        AcUtils.getString(ac, R.string.str_default_app_update_ignore) : rightBtnMsg,

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        L.v(TAG, "normal update dialog right button be clicked!!!");
                        dia.dismiss();
                    }
                });
        dia.show();
    }

    private DownloadProgressDialog proDia;
    private void showDownloadingDialog(boolean normal){
        if (AcUtils.finishing(ac)){
            return;
        }

        if (proDia == null) {
            proDia = new DownloadProgressDialog(ac);
        }
        proDia.setCancelable(normal);
        proDia.setCanceledOnTouchOutside(normal);
        if (normal) {
            proDia.setBtnMsg(AcUtils.getString(ac, R.string.str_cancel), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancelDownload = true;
                    proDia.dismiss();
                    proDia = null;
                }
            });
        }else {
            proDia.setBtnMsg("", null);
        }
        proDia.show();
    }
    private void setProcess(int len, int total){
        if (total <= 0){
            return;
        }

        if (proDia != null){
            if (len < 0){
                len = 0;
            }
            if (len > total){
                len = total;
            }

            proDia.setProgress(len * 100 / total);
            proDia.showProgress(len + "/" + total);
        }
    }

    public void downloadFile(final Handler mHandler, final String urlPath, final String downloadDir, final boolean force){
        new Thread(){
            @Override
            public void run() {
                cancelDownload = false;
                download(mHandler, urlPath, downloadDir, force);
            }
        }.start();
    }

    private void download(Handler mHandler, String urlPath, String downloadDir, boolean force) {
        BufferedInputStream bin = null;
        OutputStream out = null;
        try {
            // 统一资源
            URL url = new URL(urlPath);
            // 连接类的父类，抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            // 设定请求的方法，默认是GET
            httpURLConnection.setRequestMethod("GET");
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
            httpURLConnection.connect();

            // 文件大小
            int fileLength = httpURLConnection.getContentLength();
            sendMsg(mHandler, force ? FORCE_START : NORMAL_START, fileLength);

            // 文件名
            String filePathUrl = httpURLConnection.getURL().getFile();
            String fileFullName = filePathUrl.substring(filePathUrl.lastIndexOf(File.separatorChar) + 1);

            L.d(TAG, "file length---->" + fileLength);

            URLConnection con = url.openConnection();

            bin = new BufferedInputStream(httpURLConnection.getInputStream());

//            String path = downloadDir + File.separatorChar + fileFullName;
            File file = new File(downloadDir);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            out = new FileOutputStream(file);
            int size = 0;
            int len = 0;
            byte[] buf = new byte[1024];
            while ((size = bin.read(buf)) != -1) {
                if (cancelDownload){
                    break;
                }

                len += size;
                out.write(buf, 0, size);
                // 打印下载百分比
                // System.out.println("下载了-------> " + len * 100 / fileLength +
                // "%\n");
                sendMsg(mHandler, force ? FORCE_DOWNING : NORMAL_DOWNING, len);
            }
            sendMsg(mHandler, force ? FORCE_SUCCESS : NORMAL_SUCCESS, 0);
        } catch (MalformedURLException e) {
            L.printException(e);
            sendMsg(mHandler, force ? FORCE_FAILED : NORMAL_FAILED, -1);
        } catch (IOException e) {
            L.printException(e);
            sendMsg(mHandler, force ? FORCE_FAILED : NORMAL_FAILED, -2);
        }finally {
            try {
                if (bin != null){
                    bin.close();
                }
                if (out != null){
                    out.close();
                }
            }catch (IOException e){
                L.printException(e);
            }
        }
    }

    public final int NORMAL_START = 95;
    public final int FORCE_START = 96;
    public final int NORMAL_DOWNING = 98;
    public final int FORCE_DOWNING = 99;
    public final int NORMAL_SUCCESS = 100;
    public final int FORCE_SUCCESS = 101;
    public final int NORMAL_FAILED = -1;
    public final int FORCE_FAILED = -2;
    private int total;
    public Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == NORMAL_START){
                total = (int)msg.obj;
                setProcess(0, total);
            }else if (msg.what == FORCE_START){
                total = (int)msg.obj;
                setProcess(0, total);
            }else if (msg.what == NORMAL_DOWNING){
                setProcess((int)msg.obj, total);
            }else if (msg.what == FORCE_DOWNING){
                setProcess((int)msg.obj, total);
            }else if (msg.what == NORMAL_SUCCESS){
                AppUtils.installApk(ac, fileUrl, false);
            }else if (msg.what == FORCE_SUCCESS){
                AppUtils.installApk(ac, fileUrl, true);
            }else if (msg.what == NORMAL_FAILED){
                ac.showToast(AcUtils.getString(ac, R.string.str_default_app_update_failed));
            }else if (msg.what == FORCE_FAILED){
                showForceUpdateButton(null, null, null, null, apkUrl);
            }
        }
    };

    public boolean cancelDownload = true;
    private void sendMsg(Handler mHandler, int what, Object obj){
        if (mHandler != null){
            if (obj != null){
                if (!cancelDownload){
                    mHandler.sendMessage(mHandler.obtainMessage(what, obj));
                }
            }
        }
    }
}