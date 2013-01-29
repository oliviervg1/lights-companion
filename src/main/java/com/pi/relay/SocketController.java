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
		addMethod("turnOn()");
		addMethod("turnOff()");
	}

	@Override
	public void processInput(String methodName) { 
		switch(methodName) {
			case "powerOn()":
				powerOn();
				break;
				
			case "powerOff()":
				powerOff();
				break;
				
			default:
				System.err.println("Method not found! Check that passed methodName string is correct.");
		}
	}
	
	private void powerOn() {
		relay.turnOn();
	}
	
	private void powerOff() {
		relay.turnOff();
	}
	
}