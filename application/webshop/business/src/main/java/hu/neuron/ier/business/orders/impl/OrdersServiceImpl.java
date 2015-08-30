package hu.neuron.ier.business.orders.impl;

import java.io.Serializable;
import java.util.List;

import hu.neuron.ier.business.converter.OrdersConverter;
import hu.neuron.ier.business.orders.OrdersServiceRemote;



import hu.neuron.ier.business.vo.OrdersVO;
import hu.neuron.ier.core.dao.OrdersDao;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;


@Stateless(mappedName = "OrdersService", name = "OrdersService")
@Remote(OrdersServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class OrdersServiceImpl implements OrdersServiceRemote, Serializable {

	private static final long serialVersionUID = 8646534433898301322L;

	@Autowired
	OrdersDao ordersDao;
	
	@EJB
	OrdersConverter converter;
	
	@Override
	public OrdersVO createOrder(OrdersVO ordersVO) throws Exception {
		OrdersVO vo = converter.toVO(ordersDao.save(converter.toEntity(ordersVO)));
		return vo;
	}

	@Override
	public void deleteOrders(Long id) throws Exception {
		ordersDao.delete(id);
		
	}

	@Override
	public List<OrdersVO> getAllOrders() throws Exception {
		List<OrdersVO> vos = converter.toVO(ordersDao.findAll());
		return vos;
	}

	@Override
	public List<OrdersVO> getOrdersByStatus(String status) throws Exception {
		List<OrdersVO> vos = converter.toVO(ordersDao.findOrdersByStatus(status));
		return vos;
	}

	@Override
	public OrdersVO updateOrderStatus(Long id, String status) throws Exception {
		OrdersVO ordersVO = converter.toVO(ordersDao.findOne(id));
		ordersVO.setStatus(status);
		return createOrder(ordersVO);

	}

	@Override
	public OrdersVO updateOrderId(Long id, Long OrdersId) throws Exception {
		OrdersVO ordersVO = converter.toVO(ordersDao.findOne(id));
		ordersVO.setOrdersId(OrdersId);
		return createOrder(ordersVO);
	}

	@Override
	public OrdersVO findOrderByOrdersId(Long id) throws Exception {
		OrdersVO ordersVO = converter.toVO(ordersDao.findOrdersByOrdersId(id));
		return ordersVO;
	}

}
