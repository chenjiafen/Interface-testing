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

/**
 * @author tyler.chen
 * @version 1.0 2023-07-11
 * @date 2023-07-11 17:04
 */
public class RegisterCase extends TestBase{

   /**
    * 注册接口
    */
   @Description("用户进行注册")
   @Severity ( SeverityLevel.MINOR )
   @Test
   public  void register(){
      String json="{\"userName\":\"test\",\"password\":\"1234\",\"gender\":1,\"phoneNum\":\"110\",\"email\":\"beihe@163.com\",\"address\":\"Beijing\"}";
      HttpResponse response = request.method ( HttpMethod.POST )
              .host ( Api.Base_URL ).path ( Api.regist_url )
              .header ( "Content-Type",
                      "application/json;" )
              .data ( json ).send ();
      String body = response.body ();
      log.info("body=="+body);
      log.info ( response.statusLine() );
      JSONObject jsonObject = JSONObject.parseObject ( body );
      Integer code = jsonObject.getInteger ( "code" );
      String message =  jsonObject.getString ( "message" );
      log.info ("===code："+code+"===message:"+message);
      Assert.assertEquals(code,ResultEnum.SUCCESS_MESSAGE_REGISTER.getCode ());
      Assert.assertEquals( message,ResultEnum.SUCCESS_MESSAGE_REGISTER.getMsg ());

   }
}
