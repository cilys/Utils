package com.cily.utils.app.sql;

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
    private String logType;
    private String tag;
    private String logMsg;
    private String time;
    private String appVersionName;
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
        setTime(TimeUtils.milToStr(System.currentTimeMillis(), null));
    }

    public LogBean(String logType, String tag, String logMsg,
                   String appVersionName, String sysVersion,
                   String imei, String deviceBrand, String sysModel, String sdk) {

        setLogType(logType);
        setTag(tag);
        setLogMsg(logMsg);
        setAppVersionName(appVersionName);
        setSysVersion(sysVersion);
        setImei(imei);
        setDeviceBrand(deviceBrand);
        setSysModel(sysModel);
        setStatus("1");
        setTime(TimeUtils.milToStr(System.currentTimeMillis(), null));
        setSysSDK(sdk);
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = (time == null ? "" : time);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = (status == null ? "1" : status);
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = (appVersionName == null ? "" : appVersionName);
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
}
