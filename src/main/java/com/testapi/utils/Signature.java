package com.testapi.utils;

import java.security.MessageDigest;

/**
 * @author: chenjiafeng
 * @description
 * @Date: 2023/8/1 9:46
 */
public class Signature {
    public static String MD5(String data) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            byte[] btInput = data.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for(int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            return new String(str);
        } catch (Exception var10) {
            System.out.println("MD5 decrypt error. " + var10);
            return null;
        }
    }

    public static boolean verify(String orignData, String cipherData) {
        if (orignData != null && cipherData != null) {
            String md5 = MD5(orignData);
            return md5.equalsIgnoreCase(cipherData);
        } else {
            return false;
        }
    }
}
