package com.testapi.generic;

import com.alibaba.fastjson.JSONObject;
import com.testapi.common.Api;
import com.testapi.http.HttpMethod;
import com.testapi.http.HttpResponse;
import com.testapi.result_common.ResultEnum;
import com.testapi.utils.log;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

/**
 * @author tyler.chen
 * @version 1.0 2023-07-11
 * @date 2023-07-11 12:09
 */
public class GetSkuCase extends TestBase {

   /**
    * 获取SKU
    */
   @Description("获取SKU")
   @Severity ( SeverityLevel.CRITICAL )
   @Test
   public  void getSku(){
      HttpResponse response = request.method ( HttpMethod.GET )
              .host ( Api.Base_URL ).path ( Api.getSku_url )
              .query ( "id","1" ).send ();
      String body = response.body ();
      com.testapi.utils.log.info("body=="+body);
      com.testapi.utils.log.info ( response.statusLine() );
      JSONObject jsonObject = JSONObject.parseObject ( body );
      Integer code = jsonObject.getInteger ( "code" );
      String message =  jsonObject.getString ( "message" );
      log.info ( "==="+code );
      Assert.assertEquals(code,ResultEnum.SUCCESS_MESSAGE_SKU.getCode ());
      Assert.assertEquals( message,ResultEnum.SUCCESS_MESSAGE_SKU.getMsg ());

   }
}
