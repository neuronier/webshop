package hu.neuron.ier.business.webservice;

import hu.neuron.ier.business.webservice.vo.OrderListWebServiceVO;
import hu.neuron.ier.business.webservice.vo.OrderWebServiceVO;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "http://hu.neuron")
public interface OrderWebService {

	@WebMethod(operationName = "getOrderList")
	@WebResult(name = "getOrderList")
	public OrderListWebServiceVO getOrderListWebMethod();

	@WebMethod(operationName = "getOderByOrderId")
	@WebResult(name = "getClientByOrderId")
	public OrderWebServiceVO getOrderByOrderIdWebMethod(Long Id);

	@WebMethod(operationName = "updateOrderByOrdersId")
	@WebResult(name = "updateOrderByOrdersId")
	public OrderWebServiceVO updateOrderStatusByOrdersIdWebMethod(Long id, String status);


}
