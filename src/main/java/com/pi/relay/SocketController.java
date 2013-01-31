package com.pi.relay;

import javax.jws.WebService;

import automation.api.AbstractDevice;
import automation.api.components.RelaySwitch;

@WebService(endpointInterface = "automation.api.interfaces.ConnectedDevice")
public class SocketController extends AbstractDevice {
	
	private RelaySwitch relay;
	
	@Override
	protected void onStartup() {
		relay = new RelaySwitch();
	}
	
	public void powerOn() {
		relay.turnOn();
	}
	
	public void powerOff() {
		relay.turnOff();
	}
	
	public String getState() {
		return relay.getState();
	}
}