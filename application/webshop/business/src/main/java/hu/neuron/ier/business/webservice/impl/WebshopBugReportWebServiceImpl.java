package hu.neuron.ier.business.webservice.impl;

import hu.neuron.ier.business.bugreport.BugReportServiceRemote;
import hu.neuron.ier.business.webservice.WebshopBugReportWebService;
import hu.neuron.ier.business.webservice.vo.BugReportListWebServiceVO;
import hu.neuron.ier.business.webservice.vo.BugReportWebServiceVO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebService;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

//@Stateless(mappedName = "BugReportWebService", name = "BugReportWebService")
//@WebService(name = "WebshopBugReportWebServicePort", serviceName = "WebshopBugReportWebService", targetNamespace = "http://hu.neuron", endpointInterface = "hu.neuron.ier.business-api.webservice.WebhopBugReportWebService")
//@Interceptors(SpringBeanAutowiringInterceptor.class)
public class WebshopBugReportWebServiceImpl implements WebshopBugReportWebService {

//	@Autowired
//	@Qualifier("mapper")
//	Mapper mapper;
//
//	@EJB(mappedName = "BugReportService", name = "BugReportService")
//	BugReportServiceRemote bugReportService;
//
//	@Override
//	public BugReportListWebServiceVO getReportListWebMethod() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public BugReportWebServiceVO getBugReportByUserNameWebMethod(String userName) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	public void initEJB() {
//		try {
//			InputStream inputStream = this.getClass().getClassLoader()
//					.getResourceAsStream("hu/neuron/ier/business/Settings.properties");
//
//			Properties properties = new Properties();
//
//			try {
//				properties.load(inputStream);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//			Hashtable<String, String> env = new Hashtable<String, String>();
//			env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
//			env.put(Context.SECURITY_PRINCIPAL, properties.getProperty("SECURITY_PRINCIPAL"));
//			env.put(Context.SECURITY_CREDENTIALS, properties.getProperty("SECURITY_CREDENTIALS"));
//			env.put(Context.PROVIDER_URL, properties.getProperty("PROVIDER_URL"));
//			Context ctx;
//
//			ctx = new InitialContext(env);
//			System.out.println("ctx  = " + ctx);
//			clientService = (ClientServiceRemote) ctx
//					.lookup("ClientService#hu.neuron.ier.business.client.ClientServiceRemote");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

//	@Override
//	public ClientListWebServiceVO getClientListWebMethod() {
//		initEJB();
//		List<ClientVO> clientVOs = clientService.getClientList();
//		ClientListWebServiceVO rv = new ClientListWebServiceVO();
//		rv.setList(new ArrayList<ClientWebServiceVO>());
//		for (ClientVO clientVO : clientVOs) {
//			rv.getList().add(mapper.map(clientVO, ClientWebServiceVO.class));
//		}
//		return rv;
//	}
//
//	@Override
//	public ClientWebServiceVO createClientWebMethod(String clientId, String name, String userName,
//			String password, String email, String phoneNumber) {
//		initEJB();
//
//		ClientVO clientVO = new ClientVO();
//		clientVO.setClientId(clientId);
//		clientVO.setEmail(email);
//		clientVO.setFullName(name);
//		clientVO.setUserName(userName);
//		clientVO.setPassword(password);
//		clientVO.setPhone(phoneNumber);
//		clientVO.setEmail(email);
//
//		clientService.registrationClient(clientVO);
//
//		return mapper.map(clientVO, ClientWebServiceVO.class);
//	}
//
//	@Override
//	public ClientWebServiceVO updateClientByUserNameWebMethod(String clientId, String name,
//			String userName, String password, String email, String phoneNumber) {
//		initEJB();
//
//		ClientVO clientVO = clientService.findClientByName(userName);
//		clientVO.setClientId(clientId);
//		clientVO.setEmail(email);
//		clientVO.setFullName(name);
//		clientVO.setUserName(userName);
//		clientVO.setPassword(password);
//		clientVO.setPhone(phoneNumber);
//		clientVO.setEmail(email);
//
//		return mapper.map(clientVO, ClientWebServiceVO.class);
//	}
//
//	@Override
//	public ClientWebServiceVO getClientByUserNameWebMethod(String userName) {
//		initEJB();
//
//		return mapper.map(clientService.findClientByName(userName), ClientWebServiceVO.class);
//	}

}
