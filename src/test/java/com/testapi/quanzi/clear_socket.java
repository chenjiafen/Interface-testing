package com.testapi.quanzi;

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


public class clear_socket extends QZ_login{

    @Description("clear_socket")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void clea_test(){
        QZ_login qz_login = new QZ_login();
        qz_login.sendCode();
        qz_login.loginStatusByPhone();
        qz_login.login();
        String pqtoken = qz_login.token;
        HttpResponse response =new HttpRequest().method ( HttpMethod.POST )
                .header("Content-Type","application/json")
                .header("pqtoken",pqtoken )
                .host ( QZ_Api.Base_URL ).path ( QZ_Api.clear_url )
                .data("").send();
        String body = response.body ();
        log.info("body========="+body);
        log.info ( response.statusLine() );
        JSONObject jsonObject = JSONObject.parseObject ( body );
        Integer code = jsonObject.getInteger ( "code" );

        log.info("token===="+ pqtoken);
        Assert.assertEquals(code, ResultEnum.SUCCESS_MESSAGE.getCode ());
    }
}
