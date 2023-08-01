package com.testapi.main;

import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tyler.chen
 * @version 1.0 2023-07-11
 * @date 2023-07-11 22:52
 */
public class TestMain {
   private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
   private static String path=System.getProperty("user.dir");
   public static void main(String[] args) {
      TestNG tng = new TestNG();
      System.setProperty(ESCAPE_PROPERTY, "true");
      List<String> suites = new ArrayList<String> ();
      suites.add("D:\\chenjiafeng\\Interfacetesting\\Interfacetesting\\src\\test\\java\\testng.xml");

      tng.setTestSuites(suites);
      tng.run();

   }
}
