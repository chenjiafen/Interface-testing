package com.testapi.restful;

import com.alibaba.fastjson.JSONArray;
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
 * @date 2023-07-11 17:26
 */
public class UserListCase_a extends TestBase_a {

   @Description("获取用户list")
   @Severity ( SeverityLevel.BLOCKER )
   @Test
   public void userList(){
      HttpResponse response = request.method ( HttpMethod.GET )
              .host ( Api.Base_URL ).path ( Api.userList_url )
              .query ( "genderType","0" ).send ();
      String body = response.body ();
      log.info("body=="+body);
      log.info ( response.statusLine() );
      JSONObject jsonObject = JSONObject.parseObject ( body );
      Integer code = jsonObject.getInteger ( "code" );
      String message =  jsonObject.getString ( "message" );
      JSONArray data = jsonObject.getJSONArray ( "data" );
      String userName = data.getJSONObject ( 0 ).getString ( "userName" );
      log.info ( "========data"+data );
      log.info ( "==="+code );

      for (int i=0;i<data.size ();i++){
         String userName1 = data.getJSONObject ( i ).getString ( "userName" );
         log.info ("=========="+ userName1 );
      }
      Assert.assertEquals( code,ResultEnum.SUCCESS_MESSAGE_SKU.getCode () );
      Assert.assertEquals( message,ResultEnum.SUCCESS_MESSAGE_SKU.getMsg ());
   }
}
