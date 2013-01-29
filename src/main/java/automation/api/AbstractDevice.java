package automation.api;

import javax.jws.WebService;

import automation.api.interfaces.ConnectedDevice;

@WebService(endpointInterface = "automation.api.interfaces.ConnectedDevice")
public abstract class AbstractDevice implements ConnectedDevice{
	
	public AbstractDevice() {
		// Allows users to perform some actions when object is created
		onStartup();
	}
	
	protected void onStartup() {}
	
	@Override
	public abstract void processInput(String methodName);
}
