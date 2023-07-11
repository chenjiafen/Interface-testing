package com.testapi.utils;
import org.apache.log4j.Logger;

/**
 * @Author chenjiafneg
 * @Date 2020/7/15 17:55
 * @Version 1.0
 */
public class log {
    private static Logger Log = Logger.getLogger(log.class);

    public static void startTestCase(String sTestCaseName) {

        Log.info("---------------------------------------------------");
        Log.info("----------   " + sTestCaseName + "    ----------------");
    }

    public static void endTestCase(String sTestCaseName) {
        Log.info("----------   " + "测试用例执行结束" + "    ----------------");
        Log.info("----------------------------------------------------");
    }

    public static void info(String message) {
        Log.info(message);
    }


    public static void warn(String message) {
        Log.warn(message);
    }

    public static void error(String message) {
        Log.error(message);
    }

    public static void fatal(String message) {
        Log.fatal(message);
    }

    public static void debug(String message) {
        Log.debug(message);
    }

}
