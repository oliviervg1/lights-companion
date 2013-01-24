import com.relayClient.raspi.PowerController;
import com.relayClient.raspi.Authenticator.Authenticator;
import com.relayClient.raspi.Authenticator.Password;

public class Runner {
    public static void main(String[] args) {
        // create controller instance and start it up
        PowerController powerSocket = new PowerController();
        
        // authenticator to ensure secure access
        Authenticator authentication = new Authenticator(new Password("HomeAutomation12"));
        
        // PLACEHOLDER - will be moved client side
        authentication.encryptPassword(new Password("HomeAutomation12"));
        
        while(true) {
        	if (authentication.isPasswordCorrect()) {
        		System.out.println("");
               	System.out.println("Turn Relay ON/OFF?");
         
       	        String event = System.console().readLine();

       	        if (event.equalsIgnoreCase("on")) {
       	        	powerSocket.turnOn();
       	        }

       	        if (event.equalsIgnoreCase("off")) {
       	        	powerSocket.turnOff();
       	        }
        	}
        	else {
        		System.err.println("Authentication password is incorrect!");
        	}
        }
    }
}
