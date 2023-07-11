package com.testapi.restful;

import com.alibaba.fastjson.JSONObject;
import com.testapi.common.Api;
import com.testapi.http.HttpMethod;
import com.testapi.http.HttpResponse;
import com.testapi.result_common.ResultEnum;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author tyler.chen
 * @version 1.0 2023-07-11
 * @date 2023-07-11 16:57
 */
public class LoginCase_a extends TestBase_a {

   /**
    * 登录接口
    *
    */

   @Description("用户登录")
   @Severity ( SeverityLevel.NORMAL )
   @Test
   public  void login(){
      HttpResponse response = request.method ( HttpMethod.POST )
              .host ( Api.Base_URL ).path ( Api.login_url )
              .form ( "userName","admin" ).form ( "password","1234" ).send ();
      String body = response.body ();
      log.info("body=="+body);
      log.info ( response.statusLine() );
      JSONObject jsonObject = JSONObject.parseObject ( body );
      Integer code = jsonObject.getInteger ( "code" );
      String message =  jsonObject.getString ( "message" );
      log.info ( "==="+code );
      Assert.assertEquals(code,ResultEnum.SUCCESS_MESSAGE_SKU.getCode ());
      Assert.assertEquals( message,ResultEnum.SUCCESS_MESSAGE_SKU.getMsg ());

   }
}
