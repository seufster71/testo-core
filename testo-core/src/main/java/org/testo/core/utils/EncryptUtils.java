package org.testo.core.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testo.core.model.EnvironmentCache;


@Service
public class EncryptUtils {

	@Autowired
	EnvironmentCache environmentCache;
	
	public String getAdvSecurityId(){
		String token = "";
		try {
			String keyStr = environmentCache.getVar("ADVSecurityId");
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String input = "advs::Test::" + dateFormat.format(cal.getTime());
			token = encrypt(input, keyStr, null, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}
	
	public boolean verifyAdvSecurityId(String token) {
		boolean result = false;
		try {
			String keyStr = environmentCache.getVar("ADVSecurityId");
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			token = decrypt(token, keyStr, null, true);
			if (token != null && !token.isEmpty()){
				String[] response = token.split("::");
				Date oldDate = dateFormat.parse(response[2]);
				Calendar cal = Calendar.getInstance();
				Date newDate = cal.getTime();
				long diff = newDate.getTime() - oldDate.getTime();
         		if (diff < 900000){
         			result = true;
         		}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String encrypt(String data, String keyStr, String ivStr, boolean prependIV) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, InvalidKeySpecException{
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		byte[] iv = null;
		if (prependIV) {
			// if this true, create a random IV 
			iv = new byte[cipher.getBlockSize()];
			SecureRandom rnd = new SecureRandom();
			rnd.nextBytes(iv);
		} else {
			iv = ivStr.getBytes("UTF-8"); 
		}
		IvParameterSpec ivspec = new IvParameterSpec(iv);
		
		Key SecretKey = new SecretKeySpec(keyStr.getBytes("UTF-8"), "AES");    
		cipher.init(Cipher.ENCRYPT_MODE, SecretKey, ivspec);       
		byte[] cipherText = cipher.doFinal(data.getBytes("UTF-8"));
		
		if (prependIV){
		// prepend IV to the message
	    	byte[] mergeText = new byte[iv.length + cipherText.length];
	    	System.arraycopy(iv, 0, mergeText, 0, iv.length);
	    	System.arraycopy(cipherText, 0, mergeText, iv.length, cipherText.length);
	    	return Base64.getEncoder().encodeToString(mergeText);
	    } else {	
	    	return Base64.getEncoder().encodeToString(cipherText); 
	    }
	}

	public static String decrypt(String data, String keyStr, String ivStr, boolean prependIV) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		byte[] iv = null;
		//byte[] decodedMessage = null;
		ByteArrayInputStream bs = new ByteArrayInputStream(Base64.getDecoder().decode(data));
		if (prependIV){
			// if true pull the IV from data
			iv = new byte[16];
			bs.read(iv, 0, 16);
		} else {
			iv = ivStr.getBytes("UTF-8"); 
		}
		IvParameterSpec ivspec = new IvParameterSpec(iv);
	
	    Key SecretKey = new SecretKeySpec(keyStr.getBytes("UTF-8"), "AES");
	    cipher.init(Cipher.DECRYPT_MODE, SecretKey, ivspec);           
	
	    CipherInputStream cs = new CipherInputStream(bs, cipher);
	    BufferedReader br = new BufferedReader(new InputStreamReader(cs));
	    return br.readLine();
	    //return new String(cipher.doFinal(decodedMessage));
		
	}
}
