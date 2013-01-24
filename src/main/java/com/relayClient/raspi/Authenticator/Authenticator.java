package com.relayClient.raspi.Authenticator;

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
	
	private Password password;
	private byte[] encryptedPassword;
	private byte[] initialisationVector;
	private SecretKeySpec key;
	
	// Key has to be 16 bytes long!
	public Authenticator(Password password) {
		this.password = password;
		this.encryptedPassword = null;
		this.initialisationVector = null;
		if (password.getPassword().getBytes().length == 16) { 
			this.key = new SecretKeySpec(password.getPassword().getBytes(), "AES");
		}
		else {
			System.err.println("Password has to be exactly 16 characters long!");
			System.err.println("Password has been set to default: HomeAutomation12");
			this.key = new SecretKeySpec("HomeAutomation12".getBytes(), "AES");
		}
	}

	public void encryptPassword(Password passwordToEncrypt) {
		try {
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			encryptedPassword = cipher.doFinal(passwordToEncrypt.getPassword().getBytes());
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
	
	// TODO change when building client
	public boolean isPasswordCorrect() {
		if (password.getPassword().equals(decryptString(getEncryptedData(), getInitialisationVector()))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private String decryptString(byte[] encryptedData, byte[] initialisationVector) {
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
	
	public byte[] getEncryptedData() {
		return encryptedPassword;
	}

	public byte[] getInitialisationVector() {
		return initialisationVector;
	}
}
