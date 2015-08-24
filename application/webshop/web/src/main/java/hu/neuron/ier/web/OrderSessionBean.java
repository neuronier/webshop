package hu.neuron.ier.web;

import hu.neuron.ier.business.vo.OrdersVO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@SessionScoped
@ManagedBean(name = "orderSet")
public class OrderSessionBean {
	
	private OrdersVO settedOrder;

	public OrdersVO getSettedOrder() {
		return settedOrder;
	}

	public void setSettedOrder(OrdersVO settedOrder) {
		this.settedOrder = settedOrder;
	}
	
	

}
