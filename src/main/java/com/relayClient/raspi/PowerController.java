package com.relayClient.raspi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class PowerController {
    
    private GpioController gpio;
    private GpioPinDigitalOutput powerController;
    
    public PowerController() {
        gpio  = GpioFactory.getInstance();
        
        //   GPIO PIN #1 == POWER CONTROLLER
        powerController = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "PowerController");
        
        // force power controller to OFF if the program is shutdown
        powerController.setShutdownOptions(true,PinState.LOW);
        
        // default to off
        powerController.low();
    }
  
    public void turnOn() {
    	powerController.high();
    }
    
    public void turnOff() {
    	powerController.low();
    }
}
        