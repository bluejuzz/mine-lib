package com.skylink.minemodule.modifypassword.bean;


import com.skylink.android.commonlibrary.base.BaseRequest;

/**
 * Created by ki on 2016/10/26.
 */

public class ModifyPswdRequest extends BaseRequest {

    private String pswd;
    private String newpswd;

    public ModifyPswdRequest() {
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getNewpswd() {
        return newpswd;
    }

    public void setNewpswd(String newpswd) {
        this.newpswd = newpswd;
    }

}
