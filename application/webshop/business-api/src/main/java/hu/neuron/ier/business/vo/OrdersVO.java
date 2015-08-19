package hu.neuron.ier.business.vo;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;

public class OrdersVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3129217083180187209L;
	private Long id;
	private Long ordersId;
	private Calendar date;
	private Collection<OrderElementVO> orderElements;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOrders_id() {
		return ordersId;
	}
	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public Collection<OrderElementVO> getOrderElements() {
		return orderElements;
	}
	public void setOrderElements(Collection<OrderElementVO> orderElements) {
		this.orderElements = orderElements;
	}

}
