package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import tests.dummies.DummyAbstractDevice;

public class AbstractDeviceTest {
	
	private DummyAbstractDevice device = new DummyAbstractDevice();
	
	@Test
    public void InvokeMethod() {
		try {
			device.processInput("setCounterToOne");
		} catch (NoSuchMethodException e) { 
			fail("No such method");
		} catch (IllegalArgumentException e) {
			fail("Illegal arguments");
		}
		assertTrue(device.getCounter().equals(new Integer(1)));
    }
	
    @Test
    public void InvokeMethodWithParameters() {
    	Object[] param = {new Integer(2)};
    	try {
    		device.processInput("setCounter", param);
    	} catch (NoSuchMethodException e) { 
			fail("No such method");
		} catch (IllegalArgumentException e) {
			fail("Illegal arguments");
		}
		assertTrue(device.getCounter().equals(new Integer(2)));
    }
    
    @Test
    public void InvokeMethodWithReturn() {
    	Integer test = null;
    	try {
    		test = (Integer) device.processInput("getCounter");
    	} catch (NoSuchMethodException e) { 
			fail("No such method");
		} catch (IllegalArgumentException e) {
			fail("Illegal arguments");
		}
		assertTrue(test.equals(device.getCounter()));
    }
    
	@Test(expected = NoSuchMethodException.class)
	public void InvokeNonExistingMethod() throws NoSuchMethodException {
		device.processInput("NonExistingMethod");
	}
}
