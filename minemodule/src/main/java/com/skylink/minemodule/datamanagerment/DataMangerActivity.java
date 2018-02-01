package com.skylink.minemodule.datamanagerment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.skylink.android.commonlibrary.base.BaseActivity;
import com.skylink.android.commonlibrary.ui.AppHeader;
import com.skylink.android.commonlibrary.util.NetworkUtils;
import com.skylink.android.commonlibrary.util.SPUtils;
import com.skylink.minemodule.R;
import com.skylink.minemodule.common.Constant;

/***
 * 数据上传于下载设置
 * creat by fangj
 * on 2018/1/15
 */
public class DataMangerActivity extends BaseActivity {
    //标题
    private AppHeader mHeader;
    //手动下载
    private RelativeLayout rellayout_man;
    //手动上传
    private RelativeLayout rellayout_manUpload;
    //订单自动上传
    private RelativeLayout rellayout_orderatuupload;
    //数据自动下载
    private RelativeLayout rellayout_autodownload;
    //照片自动上传
    private RelativeLayout rellayout_photoatuupload;
    //数据删除与修复
    private RelativeLayout rellayout_datamanger;

    private TextView text_atuodownload;
    private TextView text_orderatuupload;
    private TextView text_photoatuupload;
    private TextView text_all_update;
    private TextView text_all_upload;

    private int mAutoDownloadType;
    private int mOrderAutoUploadType;
    private int mPhotoAutoUploadType;
    private final int TYPE_NO_AUTO = 1;
    private final int TYPE_WIFI = 2;
    private final int TYPE_MOBILE = 3;

    @Override
    protected void receiveParms(Bundle parms) {

    }

    @Override
    protected int setLayoutView() {
        return R.layout.activity_data_manger;
    }

    @Override
    protected void initView(View view) {
        mHeader = f(R.id.setting_ui_header);

        rellayout_man = f(R.id.setting_rellayout_man);
        rellayout_manUpload = f(R.id.setting_rellayout_manupload);
        rellayout_autodownload = f(R.id.setting_rellayout_atuodownload);
        rellayout_orderatuupload = f(R.id.setting_rellayout_orderatuupload);
        rellayout_photoatuupload = f(R.id.setting_rellayout_photoatuupload);
        rellayout_datamanger = f(R.id.setting_rellayout_datarecover);

        text_atuodownload = f(R.id.setting_txt_atuodownload);
        text_orderatuupload = f(R.id.setting_txt_orderatuupload);
        text_photoatuupload = f(R.id.setting_txt_photoatuupload);
        text_all_update = f(R.id.setting_txt_all_update);
        text_all_upload = f(R.id.setting_txt_all_upload);
    }

    @Override
    protected void initData() {
        mAutoDownloadType = SPUtils.getInstance().getInt(Constant.SPUtilsKey.KEY_AUTO_DOWNLOAD_DATA);
        mOrderAutoUploadType = SPUtils.getInstance().getInt(Constant.SPUtilsKey.KEY_AUTO_UPLOAD_ORDER);
        mPhotoAutoUploadType = SPUtils.getInstance().getInt(Constant.SPUtilsKey.KEY_AUTO_UPLOAD_PHOTO);
        //设置网络状态
        setNetTypeName(rellayout_autodownload,getTypeName(rellayout_autodownload,mAutoDownloadType));
        setNetTypeName(rellayout_orderatuupload,getTypeName(rellayout_orderatuupload,mOrderAutoUploadType));
        setNetTypeName(rellayout_photoatuupload,getTypeName(rellayout_photoatuupload,mPhotoAutoUploadType));
    }

    @Override
    protected void initListener() {
        //标题
        mHeader.setHeaderClickListener(new AppHeader.OnHeaderButtonClickListener() {
            @Override
            public void onLeftButtonClick() {
                finish();
            }

            @Override
            public void onMiddleButtonClick() {

            }

            @Override
            public void onRightButtonClick() {

            }
        });
        //手动下载
        rellayout_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkUtils.isConnected()) {
                   startActivity(DataDownloadActivity.class);
                } else {
                    showErr("亲，当前网络不可用，请确保网络良好再来试试哦！");
                }
            }
        });
        //手动上传
        rellayout_manUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkUtils.isConnected()) {
                   //startActivity(DataUploadActivity.class);
                } else {
                    showErr("亲，当前网络不可用，请确保网络良好再来试试哦！");
                }
            }
        });
        //订单自动上传
        rellayout_orderatuupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectNetType(v, mOrderAutoUploadType);
            }
        });
        //数据自动下载
        rellayout_autodownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectNetType(v, mAutoDownloadType);
            }
        });
        //照片自动上传
        rellayout_photoatuupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectNetType(v, mPhotoAutoUploadType);
            }
        });
        //数据删除与修复
        rellayout_datamanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              skipToDataManger();
            }
        });
        //一键下载
        text_all_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //一件上传
        text_all_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /***
     * 跳转数据删除与修复
     */
    private void skipToDataManger() {
        startActivity(RestoreActivity.class);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            mAutoDownloadType = SPUtils.getInstance().getInt(Constant.SPUtilsKey.KEY_AUTO_DOWNLOAD_DATA);
            mOrderAutoUploadType = SPUtils.getInstance().getInt(Constant.SPUtilsKey.KEY_AUTO_UPLOAD_ORDER);
            mPhotoAutoUploadType = SPUtils.getInstance().getInt(Constant.SPUtilsKey.KEY_AUTO_UPLOAD_PHOTO);
        }
    }

    public void setNetTypeName(View view, String typeName) {
        int i = view.getId();
        if (i == R.id.setting_rellayout_atuodownload) {
            text_atuodownload.setText(typeName);
        } else if (i == R.id.setting_rellayout_orderatuupload) {
            text_orderatuupload.setText(typeName);
        } else if (i == R.id.setting_rellayout_photoatuupload) {
            text_photoatuupload.setText(typeName);
        } else {
        }
    }

    private String getTypeName(View view, int type) {
        String s;
        switch (type) {
            case TYPE_NO_AUTO:
                s = view.getId() == R.id.setting_rellayout_atuodownload ? "不自动下载" : "不自动上传";
                break;
            case TYPE_WIFI:
                s = "仅WIFI环境";
                break;
            case TYPE_MOBILE:
                s = "移动网络(含WIFI状态)";
                break;
            default:
                s = "仅WIFI环境";
                break;
        }
        return s;
    }

    public void selectNetType(final View v, int checkedType) {
        final Dialog dialog = new Dialog(this, R.style.Translucent_NoTitle);
        View view = LayoutInflater.from(this).inflate(R.layout.dlg_select_net_type, null);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.dlg_rg_select_net_type);
        RadioButton type_no_auto = (RadioButton) view.findViewById(R.id.dlg_rb_type_no_auto);
        RadioButton type_wifi = (RadioButton) view.findViewById(R.id.dlg_rb_type_wifi);
        RadioButton type_mobile = (RadioButton) view.findViewById(R.id.dlg_rb_type_mobile);
        type_no_auto.setText(v.getId() == R.id.setting_rellayout_atuodownload ? "不自动下载" : "不自动上传");
        switch (checkedType) {
            case TYPE_NO_AUTO:
                radioGroup.check(R.id.dlg_rb_type_no_auto);
                break;
            case TYPE_WIFI:
                radioGroup.check(R.id.dlg_rb_type_wifi);
                break;
            case TYPE_MOBILE:
                radioGroup.check(R.id.dlg_rb_type_mobile);
                break;
            default:
                radioGroup.check(R.id.dlg_rb_type_wifi);
                break;
        }

        final int checkid = radioGroup.getCheckedRadioButtonId();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkid == checkedId) {
                    dialog.dismiss();
                } else {
                    if (checkedId == R.id.dlg_rb_type_no_auto) {
                        setNetTypeName(v, getTypeName(v,TYPE_NO_AUTO));
                        saveSetting(v, TYPE_NO_AUTO);

                    } else if (checkedId == R.id.dlg_rb_type_wifi) {
                      //  DataUpdateService.startCheckDataUpdateService(mContext);//开启检查待提交订单和巡店拍照的服务
                        setNetTypeName(v, getTypeName(v, TYPE_WIFI));
                        saveSetting(v, TYPE_WIFI);

                    } else if (checkedId == R.id.dlg_rb_type_mobile) {
                     //   DataUpdateService.startCheckDataUpdateService(mContext);//开启检查待提交订单和巡店拍照的服务
                        setNetTypeName(v, getTypeName(v, TYPE_MOBILE));
                        saveSetting(v, TYPE_MOBILE);

                    } else {
                    }
                    dialog.dismiss();
                }

            }
        });
        dialog.setContentView(view);
        dialog.show();
    }

    /***
     * 保存设置
     * @param view
     * @param type
     */
    private void saveSetting(View view, int type) {
        int i = view.getId();
        if (i == R.id.setting_rellayout_atuodownload) {
            SPUtils.getInstance().put(Constant.SPUtilsKey.KEY_AUTO_DOWNLOAD_DATA,type);
        } else if (i == R.id.setting_rellayout_orderatuupload) {
            SPUtils.getInstance().put(Constant.SPUtilsKey.KEY_AUTO_UPLOAD_ORDER,type);
        } else if (i == R.id.setting_rellayout_photoatuupload) {
            SPUtils.getInstance().put(Constant.SPUtilsKey.KEY_AUTO_UPLOAD_PHOTO,type);
        } else {
        }
    }
}
