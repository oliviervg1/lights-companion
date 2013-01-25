import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import automation.api.PowerController;

public class TestClient{
 
	private static final String WS_URL = "http://localhost:9999/ws/relay?wsdl";
 
	public static void main(String[] args) throws Exception {
 
		URL url = new URL(WS_URL);
        QName qname = new QName("http://relay.pi.com/", "PowerControllerImplService");
 
        Service service = Service.create(url, qname);
        PowerController powerSocket = service.getPort(PowerController.class);
 
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