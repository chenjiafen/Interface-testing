package com;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import sun.security.provider.MD5;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class test {
    public static void main(String[] args) {
        String phone="17688732017";
        String smsCode="123456";
        String code="dev#cctvj%salt";
        String timestamp="1689932834010";
        String sp=phone+"&"+smsCode+"&"+timestamp;
        Hasher hasher = Hashing.md5().newHasher();
        Hasher hasher1 = hasher.putString(sp, Charset.forName("UTF-8"));

        String hashedPassword = md5Salt(hasher1.toString(), code);
        System.out.printf(""+hashedPassword);




    }

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
}
