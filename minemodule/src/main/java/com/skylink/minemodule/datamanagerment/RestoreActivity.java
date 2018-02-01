package com.skylink.minemodule.datamanagerment;


import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.skylink.android.commonlibrary.base.BaseActivity;
import com.skylink.android.commonlibrary.entity.Session;
import com.skylink.android.commonlibrary.ui.AppHeader;
import com.skylink.minemodule.R;
import com.skylink.minemodule.common.TaskType;
import com.skylink.pdadatacentermodule.GetDataCallback;
import com.skylink.pdadatacentermodule.basedata.BaseDataService;
import com.skylink.pdadatacentermodule.venderorder.offlineorder.OfflineOrderService;
import com.skylink.pdadatacentermodule.venderorder.vendersign.VenderSignService;
import com.skylink.pdadatacentermodule.venderorder.visitphoto.VisitPhotoService;

/***
 * 数据修复和数据删除
 */
public class RestoreActivity extends BaseActivity {

    private AppHeader header;
    //删除
    private TextView button_org;
    private TextView button_customer;
    private TextView button_goods;
    private TextView button_goodsStock;
    private TextView button_groupItem;
    //修复
    private TextView button_downloadData;
    private TextView button_uploadOrderData;
    private TextView button_uploadPhotoData;
    private TextView button_signData;

    private int type;
    private final int TYPE_ORG = 1;
    private final int TYPE_CUSTOMER = 2;
    private final int TYPE_GOODS = 3;
    private final int TYPE_GOODSSTOCK = 4;
    private final int TYPE_GROUPITEM = 5;
    private final int TYPE_DOWNLOADDATA = 6;
    private final int TYPE_UPLOADORDERDATA = 7;
    private final int TYPE_UPLOADPHOTODATA = 8;
    private final int TYPE_SIGNDATA = 9;


    @Override
    protected void receiveParms(Bundle parms) {

    }

    @Override
    protected int setLayoutView() {
        return R.layout.activity_restore;
    }

    @Override
    protected void initView(View view) {
        header = findViewById(R.id.datamanagement_header);
        button_org = findViewById(R.id.datamanagement_button_org);
        button_customer = findViewById(R.id.datamanagement_button_customer);
        button_goods = findViewById(R.id.datamanagement_button_goods);
        button_goodsStock = (TextView) findViewById(R.id.datamanagement_button_goodsStock);
        button_groupItem = (TextView) findViewById(R.id.datamanagement_button_groupItem);
        button_downloadData = (TextView) findViewById(R.id.datamanagement_button_downloadData);
        button_uploadOrderData = (TextView) findViewById(R.id.datamanagement_button_uploadOrderData);
        button_uploadPhotoData = (TextView) findViewById(R.id.datamanagement_button_uploadPhotoData);
        button_signData = (TextView) findViewById(R.id.datamanagement_button_signData);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        //标题
        header.setHeaderClickListener(new AppHeader.OnHeaderButtonClickListener() {
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
       //删除机构信息
        button_org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 1;
                dealwithData();
            }
        });
        //删除客户信息
        button_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 2;
                dealwithData();
            }
        });

        //删除商品信息
        button_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 3;
                dealwithData();
            }
        });

        //删除商品库存信息
        button_goodsStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 4;
                dealwithData();
            }
        });

        /**
         * 删除价格分组信息
         */
        button_groupItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 5;
                dealwithData();
            }
        });

        //修复下载数据状态
        button_downloadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 6;
                dealwithData();
            }
        });

        //修复单据上传状态
        button_uploadOrderData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 7;
                dealwithData();
            }
        });

        //修复照片数据状态
        button_uploadPhotoData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 8;
                dealwithData();
            }
        });

        //修复签到数据
        button_signData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 9;
                dealwithData();
            }
        });

    }

    /***
     * 处理数据
     */
    private void dealwithData() {
        final Dialog dialog = new Dialog(this, R.style.Translucent_NoTitle);
        View view = LayoutInflater.from(this).inflate(R.layout.dlg_exit_datamanger, null);
        Button negativeButton = (Button) view.findViewById(R.id.dlg_btn_negative);
        Button positiveButton = (Button) view.findViewById(R.id.dlg_btn_positive);
        TextView title = (TextView) view.findViewById(R.id.dlg_tv_title);
        switch (type){
            case TYPE_ORG:
                title.setText("您确定要删除所有的机构信息吗？\r\n删除后所有账号都无法进行离线登录");
            break;
            case TYPE_CUSTOMER:
                title.setText("您确定要删除客户信息吗？");
                break;
            case TYPE_GOODS:
                title.setText("您确定要删除商品信息吗？");
                break;
            case TYPE_GOODSSTOCK:
                title.setText("您确定要删除商品库存信息吗？");
                break;
            case TYPE_GROUPITEM:
                title.setText("您确定要删除价格分组信息吗？");
                break;
            case TYPE_DOWNLOADDATA:
                title.setText("您确定要修复下载数据状态吗？");
                break;
            case TYPE_UPLOADORDERDATA:
                title.setText("您确定要修复订单数据状态吗？");
                break;
            case TYPE_UPLOADPHOTODATA:
                title.setText("您确定要修复照片数据状态吗？");
                break;
            case TYPE_SIGNDATA:
                title.setText("您确定要修复签到数据状态吗？");
                break;
        }
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                int eid = Session.getInstance().getLoginResponse().getOrginfo().getEid();
                int userId = Session.getInstance().getLoginResponse().getUserinfo().getUserid();
                switch (type){
                    case TYPE_ORG:
                        BaseDataService.getInstance().deleteOrg(new GetDataCallback<Boolean>() {
                              @Override
                              public void onSuccess(Boolean result) {
                                  if (result){
                                      showMessage("删除所有机构信息成功",true);
                                  }else {
                                      showMessage("删除所有机构信息失败",false);
                                  }
                              }

                              @Override
                              public void onFail(String errMsg) {

                              }

                              @Override
                              public void onFinish() {

                              }
                          });
                        break;
                    case TYPE_CUSTOMER:
                        BaseDataService.getInstance().deleteCustomer(eid, userId, TaskType.BUS_CUSTOMER, new GetDataCallback<Boolean>() {
                            @Override
                            public void onSuccess(Boolean result) {
                                if (result){
                                    showMessage("删除客户信息成功",true);
                                }else {
                                    showMessage("删除客户信息失败",false);
                                }
                            }

                            @Override
                            public void onFail(String errMsg) {

                            }

                            @Override
                            public void onFinish() {

                            }
                        });
                        break;
                    case TYPE_GOODS:
                        BaseDataService.getInstance().deleteGoods(eid, userId, TaskType.BUS_MYGOODS, new GetDataCallback<Boolean>() {
                            @Override
                            public void onSuccess(Boolean result) {
                                if (result){
                                    showMessage("删除商品信息成功",true);
                                }else {
                                    showMessage("删除商品信息失败",false);
                                }
                            }

                            @Override
                            public void onFail(String errMsg) {

                            }

                            @Override
                            public void onFinish() {

                            }
                        });
                        break;
                    case TYPE_GOODSSTOCK:
                        BaseDataService.getInstance().deleteGoodsStock(eid, userId, TaskType.BUS_STOCKQTY, new GetDataCallback<Boolean>() {
                            @Override
                            public void onSuccess(Boolean result) {
                                if (result){
                                    showMessage("删除商品库存信息成功",true);
                                }else {
                                    showMessage("删除商品库存信息失败",false);
                                }
                            }

                            @Override
                            public void onFail(String errMsg) {

                            }

                            @Override
                            public void onFinish() {

                            }
                        });
                        break;
                    case TYPE_GROUPITEM:
                        BaseDataService.getInstance().deleteGroupItem(eid, userId,TaskType.BUS_GROUPITEM, new GetDataCallback<Boolean>() {
                            @Override
                            public void onSuccess(Boolean result) {
                                if (result){
                                    showMessage("删除价格分组信息成功",true);
                                }else {
                                    showMessage("删除价格分组信息失败",false);
                                }
                            }

                            @Override
                            public void onFail(String errMsg) {

                            }

                            @Override
                            public void onFinish() {

                            }
                        });
                        break;
                    case TYPE_DOWNLOADDATA:
                        BaseDataService.getInstance().repairDowmloadDataStatus(eid, userId, new GetDataCallback<Boolean>() {
                            @Override
                            public void onSuccess(Boolean result) {
                                if (result){
                                    showMessage("修复下载数据状态完毕",true);
                                }
                            }

                            @Override
                            public void onFail(String errMsg) {

                            }

                            @Override
                            public void onFinish() {

                            }
                        });
                        break;
                    case TYPE_UPLOADORDERDATA:
                        OfflineOrderService.getInstance().repairOrderDataStatus(eid, userId, new GetDataCallback<Boolean>() {
                            @Override
                            public void onSuccess(Boolean result) {
                                if (result){
                                    showMessage("修复订单数据状态完毕",true);
                                }
                            }

                            @Override
                            public void onFail(String errMsg) {

                            }

                            @Override
                            public void onFinish() {

                            }
                        });
                        break;
                    case TYPE_UPLOADPHOTODATA:
                        VisitPhotoService.getInstance().repairPhotoDataStatus(eid, userId, new GetDataCallback<Boolean>() {
                            @Override
                            public void onSuccess(Boolean result) {
                                if (result){
                                    showMessage("修复照片数据状态完毕",true);
                                }
                            }

                            @Override
                            public void onFail(String errMsg) {

                            }

                            @Override
                            public void onFinish() {

                            }
                        });
                        break;
                    case TYPE_SIGNDATA:
                        VenderSignService.getInstance().repairSignDataStatus(eid, userId, new GetDataCallback<Boolean>() {
                            @Override
                            public void onSuccess(Boolean result) {
                                if (result){
                                    showMessage("修复签到数据状态完毕",true);
                                }
                            }

                            @Override
                            public void onFail(String errMsg) {

                            }

                            @Override
                            public void onFinish() {

                            }
                        });
                        break;
                }
            }
        });
        dialog.setContentView(view);
        dialog.show();
    }

    /***
     * 显示Toast
     */
    private void showMessage(String message,boolean flag){
        if (flag){
            showToast(message);
        }else {
            showErr(message);
        }
    }
}
