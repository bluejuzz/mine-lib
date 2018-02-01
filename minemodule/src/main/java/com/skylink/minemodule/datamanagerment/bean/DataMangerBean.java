package com.skylink.minemodule.datamanagerment.bean;


import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

/**
 * @author: Fangj .
 * @date: On 2018/1/15
 */

public class DataMangerBean  {

    private List<CBaseDataBean> itemList;

    public List<CBaseDataBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<CBaseDataBean> itemList) {
        this.itemList = itemList;
    }

    public static class SubItemBean extends SectionEntity<CBaseDataBean>{

        private String timeText;

        private boolean isExpand = true;//分组

        private boolean isDowload;


        public SubItemBean(boolean isHeader, String header) {
            super(isHeader, header);
        }

        public SubItemBean(CBaseDataBean itemBean) {
            super(itemBean);
        }

        public String getTimeText() {
            return timeText;
        }

        public void setTimeText(String timeText) {
            this.timeText = timeText;
        }

        public boolean isExpand() {
            return isExpand;
        }

        public void setExpand(boolean expand) {
            isExpand = expand;
        }
    }
}
