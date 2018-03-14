package com.skylink.minemodule.datamanagerment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skylink.android.commonlibrary.adapter.BaseGroupAdapter;
import com.skylink.android.commonlibrary.base.BaseActivity;
import com.skylink.android.commonlibrary.entity.Session;
import com.skylink.android.commonlibrary.ui.AppHeader;
import com.skylink.android.commonlibrary.ui.RecycleViewDivider;
import com.skylink.android.commonlibrary.util.SPUtils;
import com.skylink.android.commonlibrary.util.TimeUtils;
import com.skylink.minemodule.R;
import com.skylink.minemodule.common.Constant;
import com.skylink.minemodule.common.TaskType;
import com.skylink.minemodule.datamanagerment.bean.CBaseDataBean;
import com.skylink.minemodule.datamanagerment.bean.CBaseDataResponse;
import com.skylink.minemodule.datamanagerment.bean.DataMangerBean;
import com.skylink.minemodule.datamanagerment.bean.DataSyncBean;
import com.skylink.pdadatacentermodule.GetDataCallback;
import com.skylink.pdadatacentermodule.basedata.BaseDataService;

import java.util.ArrayList;
import java.util.List;

import static com.skylink.minemodule.common.TaskType.BUS_INVENTORY;

/***
 * 仓储数据下载
 * @author fangjin
 * @date 2018/2/25
 */
public class StockDataDownloadActivity extends BaseActivity {

    private AppHeader mHeader;
    private RecyclerView mRecyclerView;
    private BaseGroupAdapter<DataMangerBean.SubItemBean> mBeanBaseGroupAdapter;
    private CBaseDataResponse dataResponse;
    private List<DataMangerBean.SubItemBean> subItemBeanList;
    private DataSyncBean syncBean;

    @Override
    protected void receiveParms(Bundle bundle) {

    }

    private BroadcastReceiver dataSyncReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Constant.DATASNYC_RESULT)) {

                String syncMessage = intent.getStringExtra(Constant.INTENT_PARAM_KEY.KEY_DATASYNC);

                DataSyncBean dataSyncBean = new Gson().fromJson(syncMessage, new TypeToken<DataSyncBean>() {
                }.getType());
                Log.d(TAG, "Receiver:" + dataSyncBean.getBusType() + "----" + dataSyncBean.getMessage());
                if (dataResponse.getAccountInfos().size()==0){
                    searchData();
                }else {
                    syncBean = dataSyncBean;
                    syncView(dataSyncBean);
                }
            }
        }
    };

    @Override
    protected int setLayoutView() {
        return R.layout.activity_stock_data_download;
    }

    @Override
    protected void initView(View view) {
        mHeader = f(R.id.stockdatadownload_header);
        mRecyclerView = f(R.id.stockdatadownload_recyclerview);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, R.drawable.listview_divider_line));

        mBeanBaseGroupAdapter = new BaseGroupAdapter<DataMangerBean.SubItemBean>(R.layout.item_datadownload_subitem, R.layout.item_datadownload_head, null) {
            @Override
            public void convertHeader(BaseViewHolder helper, final DataMangerBean.SubItemBean item) {
                Button imag_update = helper.getView(R.id.datadownload_button_userInfoDownload);
                final RelativeLayout header_realayout = helper.getView(R.id.datadownload_rellayout_userInfo);

                helper.setText(R.id.datadownload_text_userInfo, item.header);
                helper.setText(R.id.datadownload_text_userInfoDownloadTime, item.getTimeText());

                //头部更新数据
                imag_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        datadownloadDataByType(item.header);
                    }
                });
            }

            @Override
            public void convertItem(BaseViewHolder helper, DataMangerBean.SubItemBean item) {
                CBaseDataBean dataBean = item.t;
                helper.setText(R.id.datadownload_tv_bussness, dataBean.getBusname());
                helper.setText(R.id.datadownload_text_org, dataBean.getFailmessage());

                ProgressBar progressBar = helper.getView(R.id.datadownload_progressBar_org);
                progressBar.setProgress(dataBean.getProgress());
            }
        };

        mBeanBaseGroupAdapter.setEmptyView(getEmptyView(mRecyclerView, "还未下载该企业的信息，点击一键下载开始下载吧！"));
        mRecyclerView.setAdapter(mBeanBaseGroupAdapter);
    }

    @Override
    protected void initData() {
        registerReceiver();
        searchData();
    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.DATASNYC_RESULT);
        registerReceiver(dataSyncReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(dataSyncReceiver);
    }

    private void searchData() {
        BaseDataService.getInstance().queryCCBaseDataStatus(Session.getInstance().getLoginResponse().getOrginfo().getEid(), Session.getInstance().getLoginResponse().getUserinfo().getUserid(), new GetDataCallback<String>() {
            @Override
            public void onSuccess(String result) {
                dataResponse = new Gson().fromJson(result, new TypeToken<CBaseDataResponse>() {
                }.getType());
                if (dataResponse != null) {
                    convertData();
                }
            }

            @Override
            public void onFail(String errMsg) {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    /***
     * 数据库数据转换
     */
    private void convertData() {
        if (subItemBeanList==null){
            subItemBeanList = new ArrayList<>();
        }else {
            subItemBeanList.clear();
        }
        //账户信息
        List<CBaseDataBean> accountInfos = dataResponse.getAccountInfos();
        if (accountInfos != null) {
            for (int i = 0; i < accountInfos.size(); i++) {
                CBaseDataBean dataBean = accountInfos.get(i);
                if (i == 0) {
                    DataMangerBean.SubItemBean subItemBean = new DataMangerBean.SubItemBean(true, dataBean.getGroupname());
                    subItemBean.setTimeText(dataBean.getDownloadtime());
                    subItemBeanList.add(subItemBean);
                    DataMangerBean.SubItemBean subItemBean1 = new DataMangerBean.SubItemBean(dataBean);
                    subItemBeanList.add(subItemBean1);
                } else {
                    DataMangerBean.SubItemBean subItemBean = new DataMangerBean.SubItemBean(dataBean);
                    subItemBeanList.add(subItemBean);
                }
            }
        }
        //商品信息
        List<CBaseDataBean> goodsInfos = dataResponse.getGoodsInfos();
        if (goodsInfos != null) {
            for (int i = 0; i < goodsInfos.size(); i++) {
                CBaseDataBean dataBean = goodsInfos.get(i);
                if (i == 0) {
                    DataMangerBean.SubItemBean subItemBean = new DataMangerBean.SubItemBean(true, dataBean.getGroupname());
                    subItemBean.setTimeText(dataBean.getDownloadtime());
                    subItemBeanList.add(subItemBean);
                    DataMangerBean.SubItemBean subItemBean1 = new DataMangerBean.SubItemBean(dataBean);
                    subItemBeanList.add(subItemBean1);
                } else {
                    DataMangerBean.SubItemBean subItemBean = new DataMangerBean.SubItemBean(dataBean);
                    subItemBeanList.add(subItemBean);
                }
            }
        }
        //基础资料信息
        List<CBaseDataBean> baseInfos = dataResponse.getBaseInfos();
        if (baseInfos != null) {
            for (int i = 0; i < baseInfos.size(); i++) {
                CBaseDataBean dataBean = baseInfos.get(i);
                if (i == 0) {
                    DataMangerBean.SubItemBean subItemBean = new DataMangerBean.SubItemBean(true, dataBean.getGroupname());
                    subItemBean.setTimeText(dataBean.getDownloadtime());
                    subItemBeanList.add(subItemBean);
                    DataMangerBean.SubItemBean subItemBean1 = new DataMangerBean.SubItemBean(dataBean);
                    subItemBeanList.add(subItemBean1);
                } else {
                    DataMangerBean.SubItemBean subItemBean = new DataMangerBean.SubItemBean(dataBean);
                    subItemBeanList.add(subItemBean);
                }
            }
        }
       //单据信息
        List<CBaseDataBean> orderInfos = dataResponse.getOrderInfos();
        if (orderInfos != null) {
            for (int i = 0; i < orderInfos.size(); i++) {
                CBaseDataBean dataBean = orderInfos.get(i);
                if (i == 0) {
                    DataMangerBean.SubItemBean subItemBean = new DataMangerBean.SubItemBean(true, dataBean.getGroupname());
                    subItemBean.setTimeText(dataBean.getDownloadtime());
                    subItemBeanList.add(subItemBean);
                    DataMangerBean.SubItemBean subItemBean1 = new DataMangerBean.SubItemBean(dataBean);
                    subItemBeanList.add(subItemBean1);
                } else {
                    DataMangerBean.SubItemBean subItemBean = new DataMangerBean.SubItemBean(dataBean);
                    subItemBeanList.add(subItemBean);
                }
            }
        }

        mBeanBaseGroupAdapter.setNewData(subItemBeanList);
        if (null!=syncBean){
            syncView(syncBean);
        }
    }

    @Override
    protected void initListener() {
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
                datadownloadData();
            }
        });
    }

    /***
     * 下载全部数据
     */
    private void datadownloadData() {
        List<Integer> list = new ArrayList<Integer>();

        list.add(TaskType.BUS_ORG);
        list.add(TaskType.BUS_USER);
        list.add(TaskType.BUS_STOCK);
        list.add(TaskType.BUS_MYGOODS);
        list.add(TaskType.BUS_INVENTORY);
        list.add(TaskType.BUS_STKPDYK);
        list.add(TaskType.BUS_STKPICK);

        sendBroadcast(StockDataDownloadActivity.this, new Gson().toJson(list));
    }

    /***
     * 根据业务类型下载数据
     * @param header
     */
    private void datadownloadDataByType(String header) {
        List<Integer> list = new ArrayList<>();
        switch (header) {
            case Constant.TASKTYPE.ACCOUNTINFO:
                list.add(TaskType.BUS_ORG);
                list.add(TaskType.BUS_USER);
                break;
            case Constant.TASKTYPE.GOODSINFO:
                list.add(TaskType.BUS_MYGOODS);
                list.add(TaskType.BUS_INVENTORY);
                break;
            case Constant.TASKTYPE.BASEINFO:
                list.add(TaskType.BUS_STOCK);
                break;
            case Constant.TASKTYPE.ORDERINFO:
                list.add(TaskType.BUS_STKPDYK);
                list.add(TaskType.BUS_STKPICK);
                break;
            default:
                break;
        }
        sendBroadcast(StockDataDownloadActivity.this, new Gson().toJson(list));
    }


    /***
     * 同步并刷新数据
     * @param dataSyncBean
     */
    private void syncView(DataSyncBean dataSyncBean) {

        if (dataSyncBean != null && subItemBeanList != null) {
            for (int i = 0; i < subItemBeanList.size(); i++) {
                DataMangerBean.SubItemBean subItemBean = subItemBeanList.get(i);
                //如果是头部.跳过
                if (subItemBean.isHeader) {
                    continue;
                }
                //接收数据变化的业务和列表中的业务类型一样时,改变列表数据
                if (dataSyncBean.getBusType() == subItemBean.t.getBustype()) {
                    // subItemBeanList.get(i).t.setDownloadtime(dataSyncBean.get);
                    subItemBeanList.get(i).t.setProgress(dataSyncBean.getProgress());
                    subItemBeanList.get(i).t.setStatus(dataSyncBean.getStatus());
                    subItemBeanList.get(i).t.setFailmessage(dataSyncBean.getMessage());
                }
            }
            //更新数据
            if (mBeanBaseGroupAdapter != null) {
                mBeanBaseGroupAdapter.setNewData(subItemBeanList);
            }
        }
    }

    /**
     * 发送广播
     *
     * @param
     */
    private void sendBroadcast(Context context, String param) {
        Intent intent = new Intent(Constant.DOWNLOADDATA_ACTION);
        intent.putExtra(Constant.DOWNLOAD_MESSAGE, param);
        context.sendBroadcast(intent);
        //更新下载时间
        SPUtils.getInstance().put(Constant.SPUtilsKey.KEY_DOWNLOADDATE, TimeUtils.getNowString());
    }
}
