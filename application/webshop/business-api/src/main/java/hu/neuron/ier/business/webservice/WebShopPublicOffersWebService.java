package hu.neuron.ier.business.webservice;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import hu.neuron.ier.business.webservice.vo.PublicOfferListWebServiceVO;

@WebService(name = "WebShopPublicOffersWebServicePort", serviceName = "WebShopPublicOffersWebService", targetNamespace = "http://hu.neuron")
public interface WebShopPublicOffersWebService {
	
	@WebMethod(operationName = "getPublicOfferListByName")
	@WebResult(name = "getPublicOfferListByName")
	public PublicOfferListWebServiceVO getPublicOfferListByNameWebMethod(String name);
	
	@WebMethod(operationName = "getPublicOfferList")
	@WebResult(name = "getPublicOfferList")
	public PublicOfferListWebServiceVO getAllPublicOfferWebMethod();

}
