package hu.neuron.ier.business.webservice.impl;


import hu.neuron.ier.business.orders.OrdersServiceRemote;
import hu.neuron.ier.business.vo.OrdersVO;
import hu.neuron.ier.business.webservice.WebshopOrderWebService;
import hu.neuron.ier.business.webservice.vo.WebshopOrderListWebServiceVO;
import hu.neuron.ier.business.webservice.vo.WebshopOrderWebServiceVO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "WebshopOrderWebService", name = "WebshopOrderWebService")
@WebService(name = "WebshopOrderWebServicePort", serviceName = "WebshopOrderWebService", targetNamespace = "http://hu.neuron", endpointInterface = "hu.neuron.ier.business.webservice.WebshopOrderWebService")
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class WebshopOrderWebServiceImpl implements WebshopOrderWebService {

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	@EJB(mappedName = "OrdersService", name = "OrdersService")
	OrdersServiceRemote orderService;


	@Override
	public WebshopOrderWebServiceVO getOrderByOrderIdWebMethod(Long Id) {
		WebshopOrderWebServiceVO rv = new WebshopOrderWebServiceVO();
		try {
			rv = mapper.map(orderService.findOrderByOrdersId(Id), WebshopOrderWebServiceVO.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rv;
		
	}

	public WebshopOrderListWebServiceVO getOrderListWebMethod() {
		List<OrdersVO> orderVOs = null;
		try {
			orderVOs = orderService.getOrdersByStatus("Új");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebshopOrderListWebServiceVO rv = new WebshopOrderListWebServiceVO();
		rv.setList(new ArrayList<WebshopOrderWebServiceVO>());
		for (OrdersVO orderVO : orderVOs) {
			rv.getList().add(mapper.map(orderVO, WebshopOrderWebServiceVO.class));
		}
		return rv;
	}

	@Override
	public WebshopOrderWebServiceVO updateOrderStatusByOrdersIdWebMethod(Long id,
			String status) {
		OrdersVO orderVO = null;
		try {
			orderVO = orderService.updateOrderStatus(id, status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mapper.map(orderVO, WebshopOrderWebServiceVO.class);
	}

}
