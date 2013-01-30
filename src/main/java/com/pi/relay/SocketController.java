package com.pi.relay;

import javax.jws.WebService;

import automation.api.AbstractDevice;
import automation.api.components.RelaySwitch;

@WebService(endpointInterface = "automation.api.interfaces.ConnectedDevice")
public class SocketController extends AbstractDevice {
	
	private static RelaySwitch relay;
	
	@Override
	protected void onStartup() {
		relay = new RelaySwitch();
	}
	
	static public void powerOn() {
		relay.turnOn();
	}
	
	static public void powerOff() {
		relay.turnOff();
	}
	
	static public String getState() {
		return relay.getState();
	}
}