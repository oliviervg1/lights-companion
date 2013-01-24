package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.relayClient.raspi.Authenticator;

public class AuthenticatorTest {

	@Test
	public void successfulDecryption() {
		String stringToEncrypt = "test-test";
		Authenticator authenticator = new Authenticator("testtesttesttest");
		authenticator.encryptString(stringToEncrypt);
		assertTrue(stringToEncrypt.equals(authenticator.decryptString(authenticator.getEncryptedData(), authenticator.getInitialisationVector())));
	}
	
	@Test
	public void failedDecryption() {
		String stringToEncrypt = "test-test";
		Authenticator authenticator = new Authenticator("testtesttesttest");
		authenticator.encryptString(stringToEncrypt);
		assertFalse("another-string".equals(authenticator.decryptString(authenticator.getEncryptedData(), authenticator.getInitialisationVector())));
	}

}
