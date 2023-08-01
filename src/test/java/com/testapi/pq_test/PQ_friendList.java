package com.testapi.pq_test;

import com.alibaba.fastjson.JSONObject;
import com.testapi.common.QZ_Api;
import com.testapi.http.HttpMethod;
import com.testapi.http.HttpResponse;
import com.testapi.result_common.ResultEnum;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author: chenjiafeng
 * @description 好友列表
 * @Date: 2023/7/28 17:27
 */
public class PQ_friendList extends TestBase{
    @Description("好友列表")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void friendList(String pqtoken){
        HttpResponse response = request.method ( HttpMethod.GET )
                .header("Content-Type","application/json")
                .header("pqtoken",pqtoken)
                .host ( QZ_Api.Base_URL ).path ( QZ_Api.friendList_url )
                .send();
        String body = response.body ();
        log.info("body=="+body);
        log.info ( response.statusLine() );
        JSONObject jsonObject = JSONObject.parseObject ( body );
        Integer code = jsonObject.getInteger ( "code" );
        String message =  jsonObject.getString ( "msg" );
        log.info ( "==="+code );
        Assert.assertEquals(code, ResultEnum.SUCCESS_MESSAGE.getCode ());

    }
}
