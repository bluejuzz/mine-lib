package com.skylink.minemodule.common;

/**
 * Created by guoq on 2018/1/5.
 */

public class Constant {

    public final static String DATASNYC_RESULT = "datasnyc_result";  //同步数据状态


    /**
     * 下载数据的广播ACTION
     */
    public final  static String DOWNLOADDATA_ACTION="com.skylink.basedatadownload.receiver.DownloadDataBroadcastReceiver.DownloadData";

    public final static String DOWNLOAD_MESSAGE="download_message";

    public static final String ACTION_UPDATEDATA = "com.skylink.venderorder.datasync.DataUpdateReceiver.action_updatedata";

    public static final class SPUtilsKey {
        public final static String KEY_EID = "eid";
        public final static String KEY_USERNAME = "username";
        public final static String KEY_PASSWORD = "password";
        public final static String KEY_AUTO_DOWNLOAD_DATA = "auto_download_data";
        public final static String KEY_AUTO_UPLOAD_ORDER = "auto_upload_order";
        public final static String KEY_AUTO_UPLOAD_PHOTO = "auto_upload_photo";
        public final static String KEY_AUTO_PRICE_CONVERT = "auto_price_convert";
        //下载基础数据的日期
        public final static String KEY_DOWNLOADDATE="key_downloaddate";
    }

    /**
     * intent携带参数键值对的key
     */
    public final static class INTENT_PARAM_KEY {

        /**
         * 同步数据信息
         */
        public final static String KEY_DATASYNC = "key_datasync";

        /**
         * 上传数据
         */
        public final static String KEY_UPDATETASKTYPE="key_updatetasktype";


    }

    /***
     * 下载业务类型
     */
    public static final class TASKTYPE{
        public static final String ACCOUNTINFO = "账户信息";
        public static final String GOODSINFO = "商品资料";
        public static final String CUSTORMINFO = "客户信息";
        public static final String BASEINFO = "基础资料";
        public static final String GROUPPRICEINFO = "分组价格信息";
    }
}
