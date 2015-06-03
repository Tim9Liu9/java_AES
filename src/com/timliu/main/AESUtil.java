package com.timliu.main;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


/**
 *@Title: 
 *@Description: 
 *@Copyright: Copyright (c) 2011
 *@Company:xxxx科技有限公司
 *
 * @author 
 * @version 1.0
 * @time 2015年4月3日 上午11:33:34
 */
public class AESUtil {

	/*public static void main(String[] args) {
		String password = "123456789";
		String key = "passwd123456";
		System.out.println("passwd=======   " + password);
		System.out.println("key==========   " + key);
		
		try {
			
			System.out.println(EncodeAES(password,key));
			System.out.println(DeCodeAES(miwen_password, key));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	private static byte[] Encrypt(byte[] text, byte[] key) throws Exception {
		SecretKeySpec aesKey = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); 
		cipher.init(Cipher.ENCRYPT_MODE, aesKey);
		return cipher.doFinal(text);
	}

	private static byte[] Decrypt(byte[] text, byte[] key) throws Exception {
		SecretKeySpec aesKey = new SecretKeySpec(key, "AES");
		
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, aesKey);
		return cipher.doFinal(text);
	}
	
	public static String EncodeAES(String password, String key) throws Exception{
		byte[] keybBytes = MD5STo16Byte.encrypt2MD5toByte16(key);
		byte[] passwdBytes = password.getBytes();
		byte[] aesBytyes = Encrypt(passwdBytes, keybBytes);
		return new String(Base64.encode(aesBytyes));
	}
	
	public static String DeCodeAES(String password, String key) throws Exception{
		byte[] keybBytes = MD5STo16Byte.encrypt2MD5toByte16(key);
		byte[] debase64Bytes = Base64.decode(password);
		return new String(Decrypt(debase64Bytes, keybBytes));
	}
}

