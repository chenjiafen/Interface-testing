package com.testapi.common;

public class QZ_Api {
    //host
    public static final  String Base_URL="testapi.quanzishuzi.com";

    //sendCode
    public static final  String sendCode_url="/base/sms/sendCode";

    //loginStatusByPhone
    public static final String loginStatusByPhone_url="/user/auth/loginStatusByPhone";

    //login
    public  static final String login_url="/user/auth/login";

    //clear
    public  static final String clear_url="/chat/socket/clear";

    //getParent
    public  static final String getParent_url="/circle/category/getParent";

    //blackList
    public  static final String blackList_url="/user/attention/blackList?searchWord=";

    //friendList
    public static final String friendList_url="/user/attention/friendList?searchWord=";

    //friendList
    public static final String auditMemberInfo_url="/circle/group/create/auditMember/info";
}
