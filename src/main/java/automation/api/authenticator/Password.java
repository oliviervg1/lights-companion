package automation.api.authenticator;

public class Password {
	private String password;

	public Password(String password) {
		if (password.getBytes().length == 16) {
			this.password = password;
		}
		else {
			System.err.println("Password has to be exactly 16 characters long!");
			System.err.println("Password has been set to default: HomeAutomation12");
			this.password = "HomeAutomation12";
		}
	}
		
	public String toString() {
		return password;
	}
}
