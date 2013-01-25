import javax.xml.ws.Endpoint;

import automation.api.authenticator.Authenticator;
import automation.api.authenticator.Password;

import com.pi.relay.PowerControllerImpl;

public class Runner {
    public static void main(String[] args) {
    	Endpoint.publish("http://localhost:9999/ws/hello", new PowerControllerImpl());
    }   	
}
