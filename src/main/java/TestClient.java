import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import automation.api.interfaces.PowerControllerIface;

public class TestClient{
 
	private static final String WS_URL = "http://192.168.0.9:9999/ws/SocketController?wsdl";
 
	public static void main(String[] args) throws Exception {
 
		URL url = new URL(WS_URL);
        QName qname = new QName("http://relay.pi.com/", "SocketControllerService");
 
        Service service = Service.create(url, qname);
        PowerControllerIface powerSocket = service.getPort(PowerControllerIface.class);
 
        while(true) {
        	System.out.println("Turn Relay ON/OFF?");
        	String event = System.console().readLine();
        	
        	if (event.equalsIgnoreCase("on")) {
        		powerSocket.turnOn();
        	}
        	if (event.equalsIgnoreCase("off")) {
        		powerSocket.turnOff();
        	}
        } 
    }
}