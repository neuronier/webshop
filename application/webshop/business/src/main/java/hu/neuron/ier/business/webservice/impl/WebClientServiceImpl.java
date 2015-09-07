package hu.neuron.ier.business.webservice.impl;

import hu.neuron.ier.business.client.ClientServiceRemote;
import hu.neuron.ier.business.vo.ClientVO;
import hu.neuron.ier.business.webservice.WebClientService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.xml.namespace.QName;

import neuron.hu.ClientListWebServiceVO;
import neuron.hu.ClientWebService;
import neuron.hu.ClientWebServiceVO;
import neuron.hu.ClientWebService_Service;

import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(name = "WebClientService", mappedName = "WebClientService")
@Remote(WebClientService.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class WebClientServiceImpl implements WebClientService {

	@EJB(mappedName = "ClientService", name = "ClientService")
	ClientServiceRemote clientService;

	@Override
	public List<ClientVO> getClientsFromSales() throws Exception {

		URL wsdl = null;
		List<ClientVO> clientList = new ArrayList<ClientVO>();
		try {
			wsdl = new URL("http://192.168.1.23:9101/ClientWebServiceImpl/ClientWebService?WSDL");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		QName qName = new QName("http://hu.neuron", "ClientWebService");

		ClientWebService_Service clientWebService_Service = new ClientWebService_Service(wsdl,
				qName);

		ClientWebService clientWebService = clientWebService_Service.getClientWebServicePortPort();

		ClientListWebServiceVO salesClients = clientWebService.getClientList();

		for (ClientWebServiceVO clientWebServiceVO : salesClients.getClients()) {
			ClientVO clientVO = new ClientVO();
			clientVO.setClientId(clientWebServiceVO.getClientId());
			clientVO.setEmail(clientWebServiceVO.getEmailAddress());
			clientVO.setFullName(clientWebServiceVO.getName());
			clientVO.setPassword(clientWebServiceVO.getPassword());
			clientVO.setPhone(clientWebServiceVO.getPhoneNumber());
			clientVO.setUserName(clientWebServiceVO.getUserName());
			if (clientService.findClientByName(clientVO.getUserName()) == null) {
				clientVO = clientService.registrationClient(clientVO);
				clientList.add(clientVO);
			}
		}

		return clientList;
	}

}
