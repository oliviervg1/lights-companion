package automation.api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.jws.WebService;
import automation.api.interfaces.ConnectedDevice;

@WebService(endpointInterface = "automation.api.interfaces.ConnectedDevice")
public abstract class AbstractDevice implements ConnectedDevice{
	
	private Method method;
	
	public AbstractDevice() {
		method = null;
		
		// Allows users to perform some actions when object is created
		onStartup();
	}
	
	protected abstract void onStartup();
	
	@Override
	public void processInput(String methodName) {
		try {
			method = this.getClass().getMethod(methodName);
			method.invoke(this.getClass().newInstance());
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
