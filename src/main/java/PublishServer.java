import javax.xml.ws.Endpoint;

import com.pi.relay.SocketController;

// DEPRECATED - REPLACED WITH DEPLOYABLE WAR FOR USE WITH TOMCAT
public class PublishServer {
    public static void main(String[] args) {
    	Endpoint.publish("http://192.168.0.9:9999/ws/SocketController", new SocketController());
    }   	
}
