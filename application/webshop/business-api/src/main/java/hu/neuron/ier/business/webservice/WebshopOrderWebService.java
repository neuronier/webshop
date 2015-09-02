package hu.neuron.ier.business.webservice;

import hu.neuron.ier.business.webservice.vo.WebshopOrderListWebServiceVO;
import hu.neuron.ier.business.webservice.vo.WebshopOrderWebServiceVO;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name = "WebshopOrderWebServicePort", serviceName = "WebshopOrderWebService", targetNamespace = "http://hu.neuron")
public interface WebshopOrderWebService {

	@WebMethod(operationName = "getOrderList")
	@WebResult(name = "getOrderList")
	public WebshopOrderListWebServiceVO getOrderListWebMethod();

	@WebMethod(operationName = "getOderByOrderId")
	@WebResult(name = "getClientByOrderId")
	public WebshopOrderWebServiceVO getOrderByOrderIdWebMethod(Long Id);

	@WebMethod(operationName = "updateOrderByOrdersId")
	@WebResult(name = "updateOrderByOrdersId")
	public WebshopOrderWebServiceVO updateOrderStatusByOrdersIdWebMethod(Long id, String status);


}
