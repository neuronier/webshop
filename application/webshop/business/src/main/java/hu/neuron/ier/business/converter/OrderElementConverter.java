package hu.neuron.ier.business.converter;

import hu.neuron.ier.business.vo.OrderElementVO;
import hu.neuron.ier.core.entity.OrderElement;

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
public class OrderElementConverter {

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	public OrderElementVO toVO(OrderElement orderElement) {
		if (orderElement == null) {
			return null;
		}
		return mapper.map(orderElement, OrderElementVO.class);
	}

	public OrderElement toEntity(OrderElementVO orderElementVO) {
		if (orderElementVO == null) {
			return null;
		}

		return mapper.map(orderElementVO, OrderElement.class);

	}

	public List<OrderElementVO> toVO(List<OrderElement> orderElements) {
		if (orderElements == null) {
			return null;
		}

		List<OrderElementVO> orderElementVOs = new ArrayList<OrderElementVO>();
		for (OrderElement orderElement : orderElements) {
			orderElementVOs.add(toVO(orderElement));
		}

		return orderElementVOs;
	}

	public List<OrderElement> toEntity(List<OrderElementVO> vos) {
		if (vos == null) {
			return null;
		}

		List<OrderElement> orderElement = new ArrayList<OrderElement>();
		for (OrderElementVO orderElementVO : vos) {
			orderElement.add(toEntity(orderElementVO));
		}

		return orderElement;
	}
}
