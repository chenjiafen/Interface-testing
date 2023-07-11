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

import java.util.HashMap;

/**
 * @author tyler.chen
 * @version 1.0 2023-07-11
 * @date 2023-07-11 17:13
 */
public class BuyCase_a extends TestBase_a {

   @Description("购买")
   @Severity (SeverityLevel.BLOCKER)
   @Test(dataProvider = "getBuyData")
   public  void buy(String id,String casenum,String testcase,String priority,String url,String method,String para){
      String[] split = para.split ( "=" );
      HashMap<Object, Object> hashMap = new HashMap<> ();
      for (int i=0;i<split.length-1;i++){
         hashMap.put ( split[0],split[1] );
      }
      String param = (String) hashMap.get ( "param" );
      log.info ("testcase=====>>"+ testcase );
//      String json="{\"skuId\":123,\"num\":10}";
      HttpResponse response = request.method ( HttpMethod.POST )
              .host ( Api.Base_URL ).path ( url )
              .form ( split[0],param).send ();
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
