package com.relayClient.raspi;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Authenticator {
	
	private byte[] encryptedData;
	private byte[] initialisationVector;
	private SecretKeySpec key;
	
	// Key has to be 16 bytes long!
	public Authenticator(String key) {
		this.encryptedData = null;
		this.initialisationVector = null;
		this.key = new SecretKeySpec(key.getBytes(), "AES");
	}

	public void encryptString(String stringToEncrypt) {
		try {
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			encryptedData = cipher.doFinal(stringToEncrypt.getBytes());
			initialisationVector = cipher.getIV();
		
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String decryptString(byte[] encryptedData, byte[] initialisationVector) {
		byte[] unencryptedData = null;
		byte[] iv = initialisationVector;
		
		try {
		
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
			unencryptedData = cipher.doFinal(encryptedData);
		
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new String(unencryptedData);
	}
	
	public void reset() {
		this.encryptedData = null;
		this.initialisationVector = null;
	}

	public byte[] getEncryptedData() {
		return encryptedData;
	}

	public byte[] getInitialisationVector() {
		return initialisationVector;
	}
}
