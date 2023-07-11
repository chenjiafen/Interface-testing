package com.testapi.utils;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import org.apache.commons.codec.binary.Base64;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.charset.Charset;

public class EncryptionUtil {

	public static void main(String[] args) {
//		System.out.println(md5("aaa"));
//		System.out.println(sha1("aaa"));
//		System.out.println(sha1("aaa"));
		System.out.println(base64Encode("aaaasdasdasfsdfsdfsdfsdfdf"));
		System.out.println(base64Decode("YWFhYXNkYXNkYXNmc2Rmc2Rmc2Rmc2RmZGY="));
//		System.out.println(bcrypt("aaa"));
//		System.out.println("-----aes-----");
//		String aesKey = "1234567891234567";
//		System.out.println(aesKey + "  " + aesKey.length());
//		System.out.println(aesEncode("example", aesKey));
//		System.out.println(aesDecode("IDcqXMG9R6tp5Vqi1RO92A==", aesKey));
	}

	public static String md5(String value) {

		Hasher hasher = Hashing.md5().newHasher();
		hasher.putString(value, Charset.forName("UTF-8"));
		return hasher.hash().toString();
	}

	public static String sha1(String value) {

		Hasher hasher = Hashing.sha1().newHasher();
		hasher.putString(value, Charset.forName("UTF-8"));
		return hasher.hash().toString();
	}

	public static String sha256(String value) {
		Hasher hasher = Hashing.sha256().newHasher();
		hasher.putString(value, Charset.forName("UTF-8"));
		return hasher.hash().toString();
	}

	public static String base64Encode(String value) {
		return Base64.encodeBase64String(value.getBytes());
	}

	public static String base64Decode(String value) {
		return new String(Base64.decodeBase64(value));
	}

	public static String bcrypt(String value) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(value);
	}

	public static String aesEncode(String content, String password) {
		return AES.encrypt(content, password);
	}

	public static String aesDecode(String content, String password) {
		return AES.decrypt(content, password);
	}
}
