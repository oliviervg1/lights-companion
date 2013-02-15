package automation.api.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface ConnectedDevice {
	
	@WebMethod(operationName="noParameters") Object processInput(String methodName) throws NoSuchMethodException;
	@WebMethod(operationName="withParameters") Object processInput(String methodName, Object[] parametersArray) throws NoSuchMethodException;
	
}
