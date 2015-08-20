package hu.neuron.ier.business.orderelement.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;

import org.springframework.beans.factory.annotation.Autowired;

import hu.neuron.ier.business.converter.OrderElementConverter;
import hu.neuron.ier.business.orderelement.OrderElementServiceRemote;
import hu.neuron.ier.business.vo.OrderElementVO;
import hu.neuron.ier.business.vo.OrdersVO;
import hu.neuron.ier.core.dao.OrderElementDao;

public class OrderElementServiceImpl implements OrderElementServiceRemote, Serializable {

	private static final long serialVersionUID = 9024441429565077654L;
	
	@Autowired
	OrderElementDao orderElementDao;
	
	@EJB
	OrderElementConverter converter;

	@Override
	public OrderElementVO createOrderElement(OrderElementVO orderElementVO){
		OrderElementVO vo = converter.toVO(orderElementDao.save(converter.toEntity(orderElementVO)));
		return vo;
	}

	@Override
	public void deleteOrderElement(Long id) throws Exception {
		orderElementDao.delete(id);
		
	}

	@Override
	public OrderElementVO updateOrderElementQuanty(Long id, int quanty) throws Exception {
		OrderElementVO orderElementVO = converter.toVO(orderElementDao.findOne(id));
		orderElementVO.setQuanty(quanty);
		createOrderElement(orderElementVO);
		return orderElementVO;
		
	}

	@Override
	public List<OrderElementVO> getAllOrderElement() throws Exception {
		List<OrderElementVO> vos = converter.toVO(orderElementDao.findAll());
		return vos;
	}

	@Override
	public List<OrderElementVO> getOrderElementsById(Long id) throws Exception {
		List<OrderElementVO> vos = converter.toVO(orderElementDao.findOrderElementsById(id));
		return vos;
	}

}
