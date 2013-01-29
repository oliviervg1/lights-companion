package automation.api;

import java.util.ArrayList;
import javax.jws.WebService;
import automation.api.interfaces.ConnectedDevice;

@WebService(endpointInterface = "automation.api.interfaces.ConnectedDevice")
public abstract class AbstractDevice implements ConnectedDevice{
	
	private ArrayList<String> methodList;
	
	public AbstractDevice() {
		methodList = new ArrayList<String>();
		
		// Allows users to perform some actions when object is created
		onStartup();
	}
	
	protected abstract void onStartup();
	
	@Override
	public abstract void processInput(String methodName);
	
	final protected void addMethod(String methodName) {
		methodList.add(methodName);
	}
}
