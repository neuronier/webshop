package hu.neuron.ier.business.orders;

import hu.neuron.ier.business.vo.OrdersVO;

import java.util.List;

public interface OrdersServiceRemote {

	OrdersVO createOrder(OrdersVO ordersVO) throws Exception;

	void deleteOrders(Long id) throws Exception;

	List<OrdersVO> getAllOrders() throws Exception;
	
	List<OrdersVO> getOrdersByStatus(String status) throws Exception;

	OrdersVO updateOrderStatus(Long id, String status) throws Exception;
	
	OrdersVO updateOrderId(Long id, Long OrdersId) throws Exception;

}
