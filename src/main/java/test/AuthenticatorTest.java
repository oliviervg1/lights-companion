package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.relayClient.raspi.Authenticator.AESKey;
import com.relayClient.raspi.Authenticator.Authenticator;
import com.relayClient.raspi.Authenticator.Password;

public class AuthenticatorTest {

	@Test
	public void correctPassword() {
		String stringToEncrypt = "password1";
		Authenticator authenticator = new Authenticator(new Password("password1"), new AESKey("testtesttesttest"));
		authenticator.encryptPassword(stringToEncrypt);
		assertTrue(authenticator.isPasswordCorrect());
	}
	
	@Test
	public void incorrectPassword() {
		String stringToEncrypt = "password1";
		Authenticator authenticator = new Authenticator(new Password("password2"), new AESKey("testtesttesttest"));
		authenticator.encryptPassword(stringToEncrypt);
		assertFalse(authenticator.isPasswordCorrect());
	}	
}
