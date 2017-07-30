package com.cily.utils.log;

import com.cily.utils.base.time.TimeUtils;
import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Default;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

/**
 * user:cily
 * time:2017/6/15
 * desc:
 */
@Table("t_log")
public class LogBean implements Serializable {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private String appName;
    private String appSign;     //签名信息
    private String platform;
    private String logType;
    private String tag;
    private String logMsg;
    private String errorTime;
    private String appVersion;
    private String sysVersion;
    private String imei;
    private String deviceBrand;
    private String sysModel;
    private String sysSDK;

    @Default("1")
    @Column("status")
    private String status;

    public LogBean(){
        setStatus("1");
        setErrorTime(TimeUtils.milToStr(System.currentTimeMillis(), null));
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = (appName == null ? "" : appName);
    }

    public String getAppSign() {
        return appSign;
    }

    public void setAppSign(String appSign) {
        this.appSign = (appSign == null ? "" : appSign);
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = (platform == null || platform.trim().equals("") ? "android" : platform);
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = (appVersion == null ? "" : appVersion);
    }

    public LogBean(String appName, String appSign,
                   String logType, String tag, String logMsg,
                   String appVersion, String sysVersion, String imei,
                   String deviceBrand, String sysModel, String sysSDK) {


        setAppName(appName);
        setAppSign(appSign);
        setPlatform("android");
        setLogType(logType);
        setTag(tag);
        setLogMsg(logMsg);
        setAppVersion(appVersion);
        setSysVersion(sysVersion);
        setImei(imei);
        setDeviceBrand(deviceBrand);
        setSysModel(sysModel);
        setStatus("1");
        setErrorTime(TimeUtils.milToStr(System.currentTimeMillis(), null));
        setSysSDK(sysSDK);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = (logType == null ? "" : logType);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = (tag == null ? "" : tag);
    }

    public String getLogMsg() {
        return logMsg;
    }

    public void setLogMsg(String logMsg) {
        this.logMsg = (logMsg == null ? "" : logMsg);
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = (status == null ? "1" : status);
    }



    public String getSysVersion() {
        return sysVersion;
    }

    public void setSysVersion(String sysVersion) {
        this.sysVersion = (sysVersion == null ? "" : sysVersion);
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = (imei == null ? "" : imei);
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = (deviceBrand == null ? "" : deviceBrand);
    }

    public String getSysModel() {
        return sysModel;
    }

    public void setSysModel(String sysModel) {
        this.sysModel = (sysModel == null ? "" : sysModel);
    }

    public String getSysSDK() {
        return sysSDK;
    }

    public void setSysSDK(String sysSDK) {
        this.sysSDK = sysSDK == null ? "" : sysSDK;
    }

    public String getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(String errorTime) {
        this.errorTime = errorTime;
    }
}
