package com.testapi.common;

/**
 * @author tyler.chen
 * @version 1.0 2023-07-11
 * @date 2023-07-11 11:23
 */
public class Api {
   //host
   public static final  String Base_URL="127.0.0.1:8080";

   //获取sku
   public static final  String getSku_url="/pinter/com/getSku";

   //登录接口
   public static final String login_url="/pinter/com/login";

   //会员注册
   public  static final String regist_url="/pinter/com/register";

   //购买接口
   public  static final String buy_url="/pinter/com/buy";

   //用户信息初始化
   public  static final String userInfo_url="/pinter/com/userInfo";

   //获取userList
   public  static final String userList_url="/pinter/com/userList";

}
