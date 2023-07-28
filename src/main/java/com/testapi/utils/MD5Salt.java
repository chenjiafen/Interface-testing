package com.testapi.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Salt {
    public static String md5Salt(String password, String salt) {
        try {
            // 创建消息摘要对象
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 将盐值与密码拼接
            String saltedPassword = password + salt;

            // 将字符串转换为字节数组
            byte[] bytes = saltedPassword.getBytes();

            // 更新摘要信息
            md.update(bytes);

            // 获取摘要字节数组
            byte[] digest = md.digest();

            // 将摘要字节数组转换为字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(b & 0xff);
                if (hex.length() == 1) {
                    sb.append("0");
                }
                sb.append(hex);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        String password = "123456"; // 原始密码
        String salt = "salty"; // 盐值

        String hashedPassword = md5Salt(password, salt);
        System.out.println("加盐后的MD5值：" + hashedPassword);
    }
}
