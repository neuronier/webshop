package hu.neuron.ier.business.webservice.impl;

import hu.neuron.ier.business.client.ClientServiceRemote;
import hu.neuron.ier.business.vo.ClientVO;
import hu.neuron.ier.business.webservice.WebshopClientWebService;
import hu.neuron.ier.business.webservice.vo.ClientListWebServiceVO;
import hu.neuron.ier.business.webservice.vo.ClientWebServiceVO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebService;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "ClientWebService", name = "ClientWebService")
@WebService(name = "WebshopClientWebServicePort", serviceName = "WebshopClientWebService", targetNamespace = "http://hu.neuron", endpointInterface = "hu.neuron.ier.business.webservice.WebshopClientWebService")
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class WebshopClientWebServiceImpl implements WebshopClientWebService {

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	@EJB(mappedName = "ClientService", name = "ClientService")
	ClientServiceRemote clientService;

	@Override
	public ClientListWebServiceVO getClientListWebMethod() {
		List<ClientVO> clientVOs = clientService.getClientList();
		ClientListWebServiceVO rv = new ClientListWebServiceVO();
		rv.setList(new ArrayList<ClientWebServiceVO>());
		for (ClientVO clientVO : clientVOs) {
			rv.getList().add(mapper.map(clientVO, ClientWebServiceVO.class));
		}
		return rv;
	}

	@Override
	public ClientWebServiceVO createClientWebMethod(String clientId, String name, String userName,
			String password, String email, String phoneNumber) {
		ClientVO clientVO = new ClientVO();
		clientVO.setClientId(clientId);
		clientVO.setEmail(email);
		clientVO.setFullName(name);
		clientVO.setUserName(userName);
		clientVO.setPassword(password);
		clientVO.setPhone(phoneNumber);
		clientVO.setEmail(email);

		clientService.registrationClient(clientVO);

		return mapper.map(clientVO, ClientWebServiceVO.class);
	}

	@Override
	public ClientWebServiceVO updateClientByUserNameWebMethod(String clientId, String name,
			String userName, String password, String email, String phoneNumber) {

		ClientVO clientVO = clientService.findClientByName(userName);
		clientVO.setClientId(clientId);
		clientVO.setEmail(email);
		clientVO.setFullName(name);
		clientVO.setUserName(userName);
		clientVO.setPassword(password);
		clientVO.setPhone(phoneNumber);
		clientVO.setEmail(email);

		return mapper.map(clientVO, ClientWebServiceVO.class);
	}

	@Override
	public ClientWebServiceVO getClientByUserNameWebMethod(String userName) {

		return mapper.map(clientService.findClientByName(userName), ClientWebServiceVO.class);
	}

}
