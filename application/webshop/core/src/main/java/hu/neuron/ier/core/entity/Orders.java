package hu.neuron.ier.core.entity;
import java.util.Calendar;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Class of Orders.
 */
@Entity
@Table(name = "Orders")
public class Orders extends BaseEntity {

	private static final long serialVersionUID = 891510375311558320L;
	private Long ordersId;
	private Calendar date;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "order_elements_sw")
	private Collection<OrderElement> orderElements;
	private String status;
	
	public Long getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}
	public Collection<OrderElement> getOrderElements() {
		return orderElements;
	}
	public void setOrderElements(Collection<OrderElement> orderElements) {
		this.orderElements = orderElements;
	}
	
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}