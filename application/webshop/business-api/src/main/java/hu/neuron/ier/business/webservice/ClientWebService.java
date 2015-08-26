package hu.neuron.ier.business.webservice;

import hu.neuron.ier.business.webservice.vo.ClientListWebServiceVO;
import hu.neuron.ier.business.webservice.vo.ClientWebServiceVO;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "http://hu.neuron")
public interface ClientWebService {

	@WebMethod(operationName = "getClientList")
	@WebResult(name = "getClientList")
	public ClientListWebServiceVO getClientListWebMethod();

	@WebMethod(operationName = "getClientByUserName")
	@WebResult(name = "getClientByUserName")
	public ClientWebServiceVO getClientByUserNameWebMethod(String userName);

	@WebMethod(operationName = "createClient")
	@WebResult(name = "createClient")
	public ClientWebServiceVO createClientWebMethod(String clientId, String name, String userName,
			String password, String email, String phoneNumber);

	@WebMethod(operationName = "updateClientByUserName")
	@WebResult(name = "updateClientByUserName")
	public ClientWebServiceVO updateClientByUserNameWebMethod(String clientId, String name,
			String userName, String password, String email, String phoneNumber);


}
