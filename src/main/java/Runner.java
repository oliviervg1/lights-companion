import javax.xml.ws.Endpoint;

import com.pi.relay.PowerControllerImpl;

public class Runner {
    public static void main(String[] args) {
    	Endpoint.publish("http://192.168.0.9:9999/ws/relay", new PowerControllerImpl());
    }   	
}
