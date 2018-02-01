package com.skylink.minemodule.datamanagerment.bean;

import java.io.Serializable;

/**
 * @author：guoq on 2018/1/19 21:24
 * @e-mail：guoq@myimpos.com
 * @describe:
 */

public class DataSyncBean implements Serializable {

    /* 1 下载业务 2 上传业务
    */
    private int actionType;
    private int busType=0;
    private int status;
    private int progress;
    private int course;  //1 下载数据  2 解析数据
    private String message;

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public int getBusType() {
        return busType;
    }

    public void setBusType(int busType) {
        this.busType = busType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
