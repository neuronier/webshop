package hu.neuron.ier.business.webservice.impl;


import hu.neuron.ier.business.orders.OrdersServiceRemote;
import hu.neuron.ier.business.vo.OrdersVO;
import hu.neuron.ier.business.webservice.OrderWebService;
import hu.neuron.ier.business.webservice.vo.OrderListWebServiceVO;
import hu.neuron.ier.business.webservice.vo.OrderWebServiceVO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

//@Stateless(mappedName = "OrderWebService", name = "OrderWebService")
//@WebService(name = "OrderWebServicePort", serviceName = "OrderWebService", targetNamespace = "http://hu.neuron", endpointInterface = "hu.neuron.ier.business.webservice.OrderWebService")
//@Interceptors(SpringBeanAutowiringInterceptor.class)
public class OrderWebServiceImpl implements OrderWebService {

//	@Autowired
//	@Qualifier("mapper")
//	Mapper mapper;
//
//	OrdersServiceRemote orderService;
//
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
//			orderService = (OrdersServiceRemote) ctx
//					.lookup("OrdersService#hu.neuron.ier.business.orders.OrdersServiceRemote");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public OrderWebServiceVO getOrderByOrderIdWebMethod(Long Id) {
//		initEJB();
//		OrderWebServiceVO rv = new OrderWebServiceVO();
//		try {
//			rv = mapper.map(orderService.findOrderByOrdersId(Id), OrderWebServiceVO.class);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return rv;
//		
//	}
//
//	public OrderListWebServiceVO getOrderListWebMethod() {
//		initEJB();
//		List<OrdersVO> orderVOs = null;
//		try {
//			orderVOs = orderService.getAllOrders();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		OrderListWebServiceVO rv = new OrderListWebServiceVO();
//		rv.setList(new ArrayList<OrderWebServiceVO>());
//		for (OrdersVO orderVO : orderVOs) {
//			rv.getList().add(mapper.map(orderVO, OrderWebServiceVO.class));
//		}
//		return rv;
//	}
//
//	@Override
//	public OrderWebServiceVO updateOrderStatusByOrdersIdWebMethod(Long id,
//			String status) {
//		OrdersVO orderVO = null;
//		try {
//			orderVO = orderService.updateOrderStatus(id, status);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return mapper.map(orderVO, OrderWebServiceVO.class);
//	}

}
