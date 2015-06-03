package com.timliu.main;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class TestMain {

	public static void main(String[] args) throws Exception {
//	String password = "123456789";
//	String key = "passwd123456";
//	System.out.println("passwd=======   " + password);
//	System.out.println("key==========   " + key);
//	
//	try {
//		
//		System.out.println(EncodeAES(password,key));
//		System.out.println(DeCodeAES(miwen_password, key));
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
		// "Peg5CuGq5NAeKcd+kRc8zQ==" "4zjga7af"
		
		String strEncode = EncodeAES("UMCPEwjla2wszbr", "4zjga7af");
		System.out.println("strEncode=" + strEncode);
		
		String strDecode = DeCodeAES("Peg5CuGq5NAeKcd+kRc8zQ==", "4zjga7af");
		System.out.println("strDecode=" + strDecode);
		
}

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
	byte[] debase64Bytes = Base64.decode(password);
//	System.out.println("debase64Bytes=" + debase64Bytes.length + " === " + new String(debase64Bytes));
	
	byte[] keybBytes = MD5STo16Byte.encrypt2MD5toByte16(key);
//	System.out.println("keybBytes=" + new String(keybBytes));
	
//	 ByteArrayOutputStream baos = new ByteArrayOutputStream();  
//	 DataOutputStream outputstream = new DataOutputStream(baos);  
//	 outputstream.writeChars( new String(keybBytes));     
//	 byte[] contents = baos.toByteArray(); 
	
	return new String(Decrypt(debase64Bytes, keybBytes));
}
}
