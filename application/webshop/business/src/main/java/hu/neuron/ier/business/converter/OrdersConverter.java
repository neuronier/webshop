package hu.neuron.ier.business.converter;

import hu.neuron.ier.business.vo.OrdersVO;
import hu.neuron.ier.core.entity.Orders;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.interceptor.Interceptors;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Singleton
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class OrdersConverter {

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	public OrdersVO toVO(Orders orders) {
		if (orders == null) {
			return null;
		}
		return mapper.map(orders, OrdersVO.class);
	}

	public Orders toEntity(OrdersVO ordersVO) {
		if (ordersVO == null) {
			return null;
		}

		return mapper.map(ordersVO, Orders.class);

	}

	public List<OrdersVO> toVO(List<Orders> orderss) {
		if (orderss == null) {
			return null;
		}

		List<OrdersVO> ordersVOs = new ArrayList<OrdersVO>();
		for (Orders orders : orderss) {
			ordersVOs.add(toVO(orders));
		}

		return ordersVOs;
	}

	public List<Orders> toEntity(List<OrdersVO> vos) {
		if (vos == null) {
			return null;
		}

		List<Orders> orders = new ArrayList<Orders>();
		for (OrdersVO ordersVO : vos) {
			orders.add(toEntity(ordersVO));
		}

		return orders;
	}
}
