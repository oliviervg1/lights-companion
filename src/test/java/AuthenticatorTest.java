import static org.junit.Assert.*;

import org.junit.Test;

import com.relayClient.raspi.Authenticator.Authenticator;
import com.relayClient.raspi.Authenticator.Password;

public class AuthenticatorTest {

	@Test
	public void correctPassword() {
		Password passwordToEncrypt = new Password("HomeAutomation12");
		Authenticator authenticator = new Authenticator(new Password("HomeAutomation12"));
		authenticator.encryptPassword(passwordToEncrypt);
		assertTrue(authenticator.isPasswordCorrect());
	}
	
	@Test
	public void incorrectPassword() {
		Password passwordToEncrypt = new Password("HomeAutomation34");
		Authenticator authenticator = new Authenticator(new Password("HomeAutomation12"));
		authenticator.encryptPassword(passwordToEncrypt);
		assertFalse(authenticator.isPasswordCorrect());
	}	
}
