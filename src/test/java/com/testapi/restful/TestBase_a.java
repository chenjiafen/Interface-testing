package com.testapi.restful;

import com.testapi.common.ResourcesPath;
import com.testapi.http.HttpRequest;
import com.testapi.utils.ExcelUtil;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import java.util.logging.Logger;


public class TestBase_a {
    HttpRequest request;
    Logger log = Logger.getLogger( TestBase_a.class.getName());
    public TestBase_a() {
        this.request=new HttpRequest ();
    }

    @DataProvider
    public Object[][] getSkuData() throws Exception {
        String path= ResourcesPath.path+ResourcesPath.testCase;
        ExcelUtil excel = new ExcelUtil(path);
        Object[][] ob = excel.getTestData ( "SkuIdCase" );
        return ob;
    }

    @DataProvider
    public Object[][] getBuyData() throws Exception {
        String path= ResourcesPath.path+ResourcesPath.testCase;
        ExcelUtil excel = new ExcelUtil(path);
        Object[][] ob = excel.getTestData ( "BuyCase_a" );
        return ob;
    }
    @BeforeClass
    public void BeforeClass() {
        String fileName = this.getClass().getClassLoader().getResource("log4j.xml").getPath();
        DOMConfigurator.configure(fileName);
    }




//    @Test(dataProvider ="getData" )
//    public  void getSku(String id,String casenum,String priority,String url,String method,String para) {
//
////        System.out.println (para);
//        String[] split = para.split ( "=" );
//         String s = split[1];
//
//        System.out.println (s);
//        HashMap<Object, Object> hashMap = new HashMap<> ();
//        for (int i=0;i<split.length-1;i++){
//
////            System.out.println (split[0]+"--------"+split[1]);
//            hashMap.put ( split[0],split[1] );
//        }
//
//        System.out.println (hashMap.get ( "id" ));
//
//    }
//    public static void main(String[] args) throws Exception {
//       ExcelUtil excel = new ExcelUtil("H:\\MyWork\\Test\\Interfacetesting\\testCase.xlsx");
//       Object[][] ob = excel.getTestData ( "login" );
//       for (int i=0;i<ob.length;i++){
//           Object[] ob1 = ob[i];
//           for (int j=0;j<ob1.length;j++){
//               System.out.println (ob1[j]);
//
//           }
//           System.out.println ("=======");
//       }
//    }
}
