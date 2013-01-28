package com.pi.relay;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import automation.api.devices.PowerController;

@WebService(endpointInterface = "automation.api.interfaces.PowerControllerIface")
public class SocketController extends PowerController {

	@Resource
    WebServiceContext wsctx;
	
	@Override
	public void turnOn() {
		powerController.high();
	}

	@Override
	public void turnOff() {
		powerController.low();
	}
}
