import static org.junit.Assert.*;

import org.junit.Test;

import automation.api.authenticator.Authenticator;
import automation.api.authenticator.Password;

public class AuthenticatorTest {

	@Test
	public void encryptionDecryptionTest() {
		Password passwordToEncrypt = new Password("HomeAutomation12");
		Authenticator authenticator = new Authenticator(new Password("HomeAutomation12"));
		assertTrue(passwordToEncrypt.toString().equals(
				authenticator.decryptPassword(authenticator.getEncryptedPassword(), authenticator.getInitialisationVector())));
	}	
}
