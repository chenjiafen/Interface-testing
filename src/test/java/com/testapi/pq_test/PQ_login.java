package com.testapi.pq_test;
import com.alibaba.fastjson.JSONObject;
import com.testapi.common.QZ_Api;
import com.testapi.http.HttpMethod;
import com.testapi.http.HttpRequest;
import com.testapi.http.HttpResponse;
import com.testapi.result_common.ResultEnum;
import com.testapi.utils.Signature;
import com.testapi.utils.log;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class PQ_login extends TestBase {


    String phone="17688732018";
    String smsCode="123456";
    String device="ios";
    String timestamp = String.valueOf(System.currentTimeMillis());
    String salt = "aiZ3UMryICHXbNdBLmxDEOibxvOI3Eft";
    String token;
    private static Logger Log = Logger.getLogger(log.class);
    @Test(priority=1)
    public  void sendCode() {
        String parm="{\"phone\":\""+phone+"\",\"type\":1}";
        HttpResponse response = request.method ( HttpMethod.POST )
                .header("Content-Type","application/json")
                .host ( QZ_Api.Base_URL ).path ( QZ_Api.sendCode_url )
               .data(parm).send();
        String body = response.body ();
        log.info("body=="+body);
        log.info ( response.statusLine() );
       JSONObject jsonObject = JSONObject.parseObject ( body );
       Integer code = jsonObject.getInteger ( "code" );
        String message =  jsonObject.getString ( "msg" );
        log.info ( "==="+code );
        Assert.assertEquals(code, ResultEnum.SUCCESS_MESSAGE.getCode ());
        Assert.assertEquals( message,ResultEnum.SUCCESS_MESSAGE.getMsg ());

    }

    @Test(dependsOnMethods = "sendCode")
    public  void loginStatusByPhone(){
        String parm="{\"phone\":\""+phone+"\",\"smsCode\":\""+smsCode+"\",\"device\":\"ios\"}";
        HashMap<Object, Object> map = new HashMap<>();
        map.put("phone",phone);
        map.put("smsCode",smsCode);
        map.put("device","ios");
        String jsonObject1 = JSONObject.toJSONString(map);

        System.out.println("===================="+parm);
        HttpResponse response = new HttpRequest().method ( HttpMethod.POST )
                .host ( QZ_Api.Base_URL )
                .path ( QZ_Api.loginStatusByPhone_url )
                .header("Content-Type","application/json")
                .data(parm).send();
        String body = response.body ();
        log.info("body=="+body);
        log.info ( response.statusLine() );
        JSONObject jsonObject = JSONObject.parseObject ( body );
        Integer code = jsonObject.getInteger ( "code" );
        String message =  jsonObject.getString ( "msg" );
        log.info ( "==="+code );
        Assert.assertEquals(code, ResultEnum.SUCCESS_MESSAGE.getCode ());

    }

    @Test(priority=3)
    public  void login(){
        String oriData =device+"&"+phone+"&"+ smsCode+"&"+timestamp+salt;
        System.out.println("oriData===="+oriData);
        Signature signature = new Signature();
        String sign = Signature.MD5(signature.MD5(oriData)+salt);
        log.info("------------"+sign);
        String parm="{\"phone\":\""+phone+"\",\"smsCode\":\""+smsCode+"\",\"device\":\"ios\",\"inviteCode\":\"\",\"proxyUserCouponId\":\"\",\"type\":\"\"}";
        HttpResponse response =new HttpRequest().method ( HttpMethod.POST )
                .header("Content-Type","application/json")
                .header("timestamp",timestamp)
                .header("sign",sign)
                .host ( QZ_Api.Base_URL ).path ( QZ_Api.login_url )
                .data(parm).send();
        String body = response.body ();
        log.info("body========="+body);
        log.info ( response.statusLine() );
        JSONObject jsonObject = JSONObject.parseObject ( body );
        Integer code = jsonObject.getInteger ( "code" );
        token = jsonObject.getJSONObject("data").getString("tokenValue");
        log.info("token===="+token);
        Assert.assertEquals(code, ResultEnum.SUCCESS_MESSAGE.getCode ());
    }
}
