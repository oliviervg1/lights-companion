import static org.junit.Assert.*;

import org.junit.Test;

import automation.api.authenticator.Authenticator;
import automation.api.authenticator.Password;

public class AuthenticatorTest {

	@Test
	public void encryptionDecryptionTest() {
		Password passwordToEncrypt = new Password("HomeAutomation12");
		Authenticator authenticator = new Authenticator(new Password("HomeAutomation12"));
		authenticator.encryptPassword(passwordToEncrypt);
		assertTrue(passwordToEncrypt.getPassword().equals(
				authenticator.decryptPassword(authenticator.getEncryptedData(), authenticator.getInitialisationVector())));
	}	
}
