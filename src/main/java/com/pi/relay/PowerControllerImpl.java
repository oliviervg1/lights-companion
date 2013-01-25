package com.pi.relay;

import automation.api.PowerController;
import automation.api.authenticator.Authenticator;
import automation.api.authenticator.Password;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

//Service Implementation Bean
@WebService(endpointInterface = "automation.api.PowerController")
public class PowerControllerImpl implements PowerController {
	
	@Resource
    WebServiceContext wsctx;
    
	private Authenticator authentication;
    private GpioController gpio;
    private GpioPinDigitalOutput powerController;
    
    public PowerControllerImpl() { 
    	authentication = new Authenticator(new Password("HomeAutomation12"));
    	gpio  = GpioFactory.getInstance();
        
        //   GPIO PIN #1 == POWER CONTROLLER
        powerController = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "PowerController");
        
        // force power controller to OFF if the program is shutdown
        powerController.setShutdownOptions(true,PinState.LOW);
        
        // default to off
        powerController.low();
    }
  
    @Override
    public void turnOn() { 
    	//MessageContext mctx = wsctx.getMessageContext();
    	//if (authentication.isPasswordCorrect(mctx)) {
    		powerController.high();
    	//}
    }
    
    @Override
    public void turnOff() {
    	//MessageContext mctx = wsctx.getMessageContext();
    	//if (authentication.isPasswordCorrect(mctx)) {
    		powerController.low();
    	//}
    }
}
        