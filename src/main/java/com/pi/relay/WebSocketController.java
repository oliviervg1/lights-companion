package com.pi.relay;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import automation.api.interfaces.ConnectedDevice;

public class WebSocketController {
	
	private static final String WS_URL = "http://192.168.0.9:8080/relay-control-1.0.0/SocketController?wsdl";
	private URL url;
	private QName qname;
	private Service service;
	private ConnectedDevice powerSocket;
	
	public WebSocketController() {
 		
		try {
			url = new URL(WS_URL);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        qname = new QName("http://relay.pi.com/", "SocketControllerService");
 
        service = Service.create(url, qname);
        powerSocket = service.getPort(ConnectedDevice.class);
	}
	
	public void turnOn() {
		powerSocket.processInput("powerOn");
	}
	
	public void turnOff() {
		powerSocket.processInput("powerOff");
	}
	
	public String getState() {
		return (String) powerSocket.processInput("getState");
	}
}
