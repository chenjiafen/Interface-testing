package com.testapi.pq_test;

import com.alibaba.fastjson.JSONObject;
import com.testapi.common.QZ_Api;
import com.testapi.http.HttpMethod;
import com.testapi.http.HttpRequest;
import com.testapi.http.HttpResponse;
import com.testapi.result_common.ResultEnum;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestCase{
    String pqtoken;
    @Description("TestCase")
    @Severity(SeverityLevel.BLOCKER)
    @Test(priority=1)
    public void testcase01() {
        PQ_login login = new PQ_login();
        PQ_clear clear = new PQ_clear();
        PQ_getParent getParent = new PQ_getParent();
        PQ_BlackList blackList = new PQ_BlackList();
        PQ_friendList friendList = new PQ_friendList();
        login.sendCode();
        login.loginStatusByPhone();
        login.login();
        pqtoken = login.token;
        clear.clear(pqtoken);
        getParent.getParent(pqtoken);
        blackList.blackList(pqtoken);
        friendList.friendList(pqtoken);
    }
    @Description("TestCase01")
    @Severity(SeverityLevel.BLOCKER)
    @Test(priority=2,dependsOnMethods = "testcase01")
    public void testcase02() {
     PQ_AuditMemberInfo auditMemberInfo=new PQ_AuditMemberInfo();
     auditMemberInfo.auditMemberInfo(pqtoken);
    }
}
