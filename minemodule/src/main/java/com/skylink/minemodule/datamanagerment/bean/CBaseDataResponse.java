package com.skylink.minemodule.datamanagerment.bean;

import java.util.List;

/**
 * @author: Fangj .
 * @date: On 2018/1/15
 */

public class CBaseDataResponse {
    private List<CBaseDataBean> accountInfos;
    private List<CBaseDataBean> goodsInfos;
    private List<CBaseDataBean> customerInfos;
    private List<CBaseDataBean> baseInfos;
    private List<CBaseDataBean> groupPriceInfos;
    private List<CBaseDataBean> orderInfos;

    public List<CBaseDataBean> getAccountInfos() {
        return accountInfos;
    }

    public void setAccountInfos(List<CBaseDataBean> accountInfos) {
        this.accountInfos = accountInfos;
    }

    public List<CBaseDataBean> getGoodsInfos() {
        return goodsInfos;
    }

    public void setGoodsInfos(List<CBaseDataBean> goodsInfos) {
        this.goodsInfos = goodsInfos;
    }

    public List<CBaseDataBean> getCustomerInfos() {
        return customerInfos;
    }

    public void setCustomerInfos(List<CBaseDataBean> customerInfos) {
        this.customerInfos = customerInfos;
    }

    public List<CBaseDataBean> getBaseInfos() {
        return baseInfos;
    }

    public void setBaseInfos(List<CBaseDataBean> baseInfos) {
        this.baseInfos = baseInfos;
    }

    public List<CBaseDataBean> getGroupPriceInfos() {
        return groupPriceInfos;
    }

    public void setGroupPriceInfos(List<CBaseDataBean> groupPriceInfos) {
        this.groupPriceInfos = groupPriceInfos;
    }

    public List<CBaseDataBean> getOrderInfos() {
        return orderInfos;
    }

    public void setOrderInfos(List<CBaseDataBean> orderInfos) {
        this.orderInfos = orderInfos;
    }
}
