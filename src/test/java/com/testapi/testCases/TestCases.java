package com.testapi.testCases;

import com.testapi.generic.*;
import com.testapi.restful.BuyCase_a;
import com.testapi.restful.GetSkuCase_a;
import io.qameta.allure.*;
import org.testng.annotations.Test;

/**
 * @author tyler.chen
 * @version 1.0 2023-07-11
 * @date 2023-07-11 18:16
 */
public class TestCases {

   @Epic( "购买目录" )
   @Story( "进行购买" )
   @Description("用户购买流程")
   @Severity( SeverityLevel.BLOCKER )
   @Test
   public void shopping(){
      BuyCase buyCase = new BuyCase ();
      GetSkuCase getSkuCase = new GetSkuCase ();
      getSkuCase.getSku ();
      buyCase.buy ();

   }
   @Epic( "购买目录" )
   @Story( "进行购买" )
   @Description("下单购买")
   @Severity( SeverityLevel.BLOCKER )
   @Test
   public void Ordering (){
      LoginCase  loginCase= new LoginCase ();
      RegisterCase registerCase = new RegisterCase ();

      loginCase.login ();
      registerCase.register ();

   }
   @Epic( "购买目录" )
   @Story( "进行购买" )
   @Description("下单购买")
   @Severity( SeverityLevel.BLOCKER )
   @Test
   public void shouhou (){
      UserInfoCase  userInfoCase= new UserInfoCase ();
      UserListCase userListCase = new UserListCase ();

      userInfoCase.userInfo ();
      userListCase.userList ();

   }

   @Epic( "购买目录" )
   @Story( "进行购买" )
   @Description("查询sku并加入购物车")
   @Severity( SeverityLevel.BLOCKER )
   @Test
   public void getSKUAndBuy (){
      BuyCase_a buyCase_a = new BuyCase_a ();
      GetSkuCase_a getSkuCase_a = new GetSkuCase_a ();
      buyCase_a.buy ( "1","购买","购买sku=123","H","/pinter/com/buy","POST","param={\"skuId\":123,\"num\":10}");
      getSkuCase_a.getSku ( "1","获取SKU","当等于1时获取sku","H","/pinter/com/getSku","GET","id=1" );
   }
}
