package com.skylink.minemodule.common;

/**
 * @author：guoq on 2018/1/19 21:25
 * @e-mail：guoq@myimpos.com
 * @describe:
 */

public class TaskType {

    /**
     * 下载任务
     */
    public final  static int ACTION_DOWNLOAD=1;

    /**
     * 上传任务
     */
    public final  static int ACTION_UPLOAD=2;


    /**
     * 机构信息
     */
    public final  static int BUS_ORG=1101;
    public final  static String BUS_ORG_TXT="t_sys_org.txt";
    public final  static String BUSNAME_ORG="机构信息";
    /**
     * 用户信息
     */
    public final  static int BUS_USER=1102;
    public final  static String BUS_USER_TXT="t_sys_user.txt";
    public final  static String BUSNAME_USER="用户信息";
    /**
     * 用户机构
     */
    public final  static int BUS_USERORG=1103;
    public final  static String BUS_USERORG_TXT="t_sys_userorg.txt";
    public final  static String BUSNAME_USERORG="用户机构";
    /**
     * 用户部门权限
     */
    public final  static int BUS_USERDEPT=1104;
    public final  static String BUS_USERDEPT_TXT="t_sys_userdept.txt";
    public final  static String BUSNAME_USERDEPT="用户部门权限";


    /**
     * 仓库信息
     */
    public final  static int BUS_STOCK=2101;
    public final  static String BUS_STOCK_TXT="t_bas_stock.txt";
    public final  static String BUSNAME_STOCK="仓库信息";

    /**
     * 数据字典
     */
    public final  static int BUS_PARAITEM=2102;
    public final  static String BUS_PARAITEM_TXT="t_sys_paraitem.txt";
    public final  static String BUSNAME_PARAITEM="数据字典";


    /**
     * 商品分类信息
     */
    public final  static int BUS_MYCATEGORY=3101;
    public final  static String BUS_MYCATEGORY_TXT="t_bas_mycategory.txt";
    public final  static String BUSNAME_MYCATEGORY="商品分类信息";

    /**
     * 商品信息
     */
    public final  static int BUS_MYGOODS=3102;
    public final  static String BUS_MYGOODS_TXT="t_bas_mygoods.txt";
    public final  static String BUSNAME_MYGOODS="商品信息";

    /**
     * 商品库存信息
     */
    public final  static int BUS_STOCKQTY=3103;
    public final  static String BUS_STOCKQTY_TXT="t_ven_stockqty.txt";
    public final  static String BUSNAME_STOCKQTY="商品库存信息";


    /**
     * 客户信息
     */
    public final  static int BUS_CUSTOMER=4101;
    public final  static String BUS_CUSTOMER_TXT="t_bas_customer.txt";
    public final  static String BUSNAME_CUSTOMER="客户信息";

    /**
     * 分销商信息
     */
    public final  static int BUS_AGENTORG=4102;
    public final  static String BUS_AGENTORG_TXT="t_sys_agentorg.txt";
    public final  static String BUSNAME_AGENTORG="分销商信息";

    /**
     * 客户拜访信息
     */
    public final  static int BUS_VISITTASK=4103;
    public final  static String BUS_VISITTASK_TXT="t_ven_visittask.txt";
    public final  static String BUSNAME_VISITTASK="客户拜访信息";

    /**
     * 供应商客户分组资料
     */
    public final  static int BUS_GROUPBRANCH=4104;
    public final  static String BUS_GROUPBRANCH_TXT="t_ven_groupbranch.txt";
    public final  static String BUSNAME_GROUPBRANCH="供应商客户分组资料";


    /**
     * 分组价格信息
     */
    public final  static int BUS_GROUPITEM=5101;
    public final  static String BUS_GROUPITEM_TXT="t_ven_groupitem.txt";
    public final  static String BUSNAME_GROUPITEM="分组价格信息";

    /**
     * 历史价格信息
     */
    public final  static int BUS_GPRICEHIS=5102;
    public final  static String BUS_GPRICEHIS_TXT="t_ven_gpricehis.txt";
    public final  static String BUSNAME_GPRICEHIS="历史价格信息";


    /**
     * 上传图片
     */
    public final  static int BUS_UPLOADPHOTO=9101;


    /**
     * 上传订单数据
     */
    public final  static int BUS_UPLOADORDER=9102;

    /**
     * 上传签到数据
     */
    public final  static int BUS_SIGN=9103;
}
