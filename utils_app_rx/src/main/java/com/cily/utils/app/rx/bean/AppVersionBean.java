package com.cily.utils.app.rx.bean;

import java.io.Serializable;

/**
 * user:cily
 * time:2017/7/4
 * desc:
 */

public class AppVersionBean implements Serializable {
    private String version; //新版本的版本号
    private int status;     //状态，1为普通更新，2为强制更新
    private String apkUrl;  //新版本的下载地址
    private String updateTitle;  //版本更新弹框的标题
    private String updateMsg;   //版本更新弹框的信息

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getUpdateMsg() {
        return updateMsg;
    }

    public void setUpdateMsg(String updateMsg) {
        this.updateMsg = updateMsg;
    }

    public String getUpdateTitle() {
        return updateTitle;
    }

    public void setUpdateTitle(String updateTitle) {
        this.updateTitle = updateTitle;
    }
}
