package automation.api.components;

import automation.api.interfaces.Switch;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class RelaySwitch implements Switch {
    
    private GpioController gpio;
    private GpioPinDigitalOutput powerSwitch;
    
    public RelaySwitch() { 
    	gpio  = GpioFactory.getInstance();
        
        //   GPIO PIN #1 == POWER CONTROLLER
        powerSwitch = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "PowerSwitch");
        
        // force power controller to OFF if the program is shutdown
        powerSwitch.setShutdownOptions(true,PinState.LOW);
        
        // default to off
        powerSwitch.low();
    }
  
    @Override
    public void turnOn() {
    	powerSwitch.high();
    }
    
    @Override
    public void turnOff() {
    	powerSwitch.low();
    }
    
    public String getState() {
    	if (powerSwitch.isHigh()) {
    		return "On";
    	}
    	else {
    		return "Off";
    	}
    }
}
        