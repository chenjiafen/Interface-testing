package com.testapi.common.pq_base_server;

/**
 * @author: chenjiafeng
 * @description：版本升级Controller
 * @Date: 2023/7/28 15:33
 */
public class Upgrade_URL {


    //查询版本升级列表
    public static final  String version_list="/base/version/list";

    //导出
    public static final String version_export="/base/version/export";

    //获取版本号列表
    public  static final String no_list="/base/version/no/list";

    //获取版本升级详细信息/删除版本升级
    public  static final String buy_url="/base/version/{id}";

    //新增版本升级
    public  static final String userInfo_url="/base/version";

    //修改版本升级
    public  static final String userList_url="/pinter/com/userList";
}
