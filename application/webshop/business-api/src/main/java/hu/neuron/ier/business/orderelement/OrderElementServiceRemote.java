package hu.neuron.ier.business.orderelement;

import hu.neuron.ier.business.vo.OrderElementVO;

import java.util.List;

public interface OrderElementServiceRemote {

	OrderElementVO createOrderElement(OrderElementVO orderElementVO) throws Exception;

	void deleteOrderElement(Long id) throws Exception;
	
	OrderElementVO updateOrderElementQuanty(Long id, int quanty) throws Exception;

	List<OrderElementVO> getAllOrderElement() throws Exception;
	
	List<OrderElementVO> getOrderElementsById(Long id) throws Exception;
}
