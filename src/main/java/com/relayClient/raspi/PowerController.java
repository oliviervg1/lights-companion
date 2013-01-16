package com.relayClient.raspi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.trigger.GpioToggleStateTrigger;

public class PowerController
{
    // internal class members
    private GpioController gpio;
    private GpioPinDigitalInput overrideSwitch;
    private GpioPinDigitalOutput powerController;
    
    
    public PowerController() {
    	// create GPIO controller
        gpio  = GpioFactory.getInstance();
        
        // provision GPIO pins : 
        //   GPIO PIN #0 == OVERRIDE SWITCH
        //   GPIO PIN #1 == POWER CONTROLLER
        overrideSwitch = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00, "OverrideSwitch", PinPullResistance.PULL_DOWN);
        powerController = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "PowerController");
        
        // force power controller to OFF if the program is shutdown
        powerController.setShutdownOptions(true,PinState.LOW);
        
        // create a gpio toggle trigger on the override switch input pin; 
        // when the input is detected, toggle the power controller state
        overrideSwitch.addTrigger(new GpioToggleStateTrigger(PinState.HIGH, powerController));
        
        // create a listener for the override switch
        overrideSwitch.addListener(new OverrideSwitchListener());
        
        while(true) {
            System.out.println("");
        	System.out.println("Turn Relay ON/OFF?");
  
	        String event = System.console().readLine();
	        
	        if (event == "On" || event == "on") {
	        	powerController.high();
	        }
	        
	        if (event == "Off" || event == "off") {
	        	powerController.low();
	        }
        }
    }
  
//    public void turnOn() {
//    	powerController.high();
//    }
//    
//    public void turnOff() {
//    	powerController.low();
//    }
    
    /**
     * This listener class is invoked as a callback when a state change
     * is detected on the override input switch (if implemented; optional)
     * 
     * @author Robert Savage
     */
    private class OverrideSwitchListener implements GpioPinListenerDigital
    {
        @Override
        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event)
        {
            if(event.getState().isHigh())
            {
                System.out.println("---------------------------------");
                System.out.println("[OVERRIDE] POWER STATE TOGGLED");
                System.out.println("---------------------------------");
            }
        }
    }  
}
        