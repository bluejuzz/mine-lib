package com.skylink.minemodule.datamanagerment.bean;

/**
 * @author: Fangj .
 * @date: On 2018/1/15
 */

public class CBaseDataBean {
    private int bustype;         //返回结果
    private String busname;      //业务类型
    private String downloadtime; //业务名称
    private int status;          //上次下载时间
    private String failmessage;  //是否下载完成
    private String groupname;  //分组名称
    private int progress=0;



    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public int getBustype() {
        return bustype;
    }

    public void setBustype(int bustype) {
        this.bustype = bustype;
    }

    public String getBusname() {
        return busname;
    }

    public void setBusname(String busname) {
        this.busname = busname;
    }

    public String getDownloadtime() {
        return downloadtime;
    }

    public void setDownloadtime(String downloadtime) {
        this.downloadtime = downloadtime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFailmessage() {
        return failmessage;
    }

    public void setFailmessage(String failmessage) {
        this.failmessage = failmessage;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
