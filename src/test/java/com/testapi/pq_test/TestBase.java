package com.testapi.pq_test;

import com.testapi.http.HttpRequest;
import com.testapi.restful.TestBase_a;
import com.testapi.utils.log;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.BeforeClass;

import java.util.logging.Logger;


public class TestBase {
    HttpRequest request;
    Logger log = Logger.getLogger( TestBase_a.class.getName());
    public TestBase() {
        this.request=new HttpRequest ();
    }


    @BeforeClass
    public void BeforeClass() {
        String fileName = this.getClass().getClassLoader().getResource("log4j.xml").getPath();
        DOMConfigurator.configure(fileName);
    }


}
