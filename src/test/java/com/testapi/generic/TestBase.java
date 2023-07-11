package com.testapi.generic;

import com.testapi.http.HttpRequest;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.BeforeClass;

import java.util.logging.Logger;


public class TestBase {
    HttpRequest request;
    Logger log = Logger.getLogger(TestBase.class.getName());
    public TestBase() {
        this.request=new HttpRequest ();
    }

//    @DataProvider
//    public Object[][] getData(Method method) throws Exception {
//        ExcelUtil excel = new ExcelUtil("C:/Users/LXG/Desktop/liecai.xlsx");
//        Object[][] result = null;
//        if (method.getName().equals("test001_login")) {
//            result = excel.getTestData("login");
//        } else if (method.getName().equals("test004_search")) {
//            result = excel.getTestData("search");
//        } else {
//            result = new Object[][]{new Object[]{3}};
//        }
//        return result;
//    }

    @BeforeClass
    public void BeforeClass() {
        String fileName = this.getClass().getClassLoader().getResource("log4j.xml").getPath();
        DOMConfigurator.configure(fileName);
    }


}
