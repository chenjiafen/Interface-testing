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
 * @date 2023-07-11 17:56
 */
public class UserInfoCase_a extends TestBase_a {

   @Description("用户初始化")
   @Severity ( SeverityLevel.TRIVIAL )
   @Test
   public void userInfo(){

      String json="{\"phoneNum\":\"123434\",\"optCode\":\"testfan\",\"timestamp\":\"12112121212\",\"sign\":\"your sign data\"}";
      HttpResponse response = request.method ( HttpMethod.POST )
              .host ( Api.Base_URL ).path ( Api.userInfo_url )
              .header ( "Content-Type",
              "application/json;" )
              .data ( json ).send ();
      String body = response.body ();
      log.info("body=="+body);
      log.info ( response.statusLine() );
      JSONObject jsonObject = JSONObject.parseObject ( body );
      Integer code = jsonObject.getInteger ( "code" );
      String message =  jsonObject.getString ( "message" );
      log.info ( "==="+code );

      //SUCCESS_MESSAGE_SKU.getCode ()是预期结果
      Assert.assertEquals(ResultEnum.SUCCESS_MESSAGE_SKU.getCode (),code);
   }
}
