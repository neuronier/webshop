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
// @NamedQuery(name = "Orders.findOfferByID", query =
// "SELECT o FROM Offer o  WHERE o.id = :id")
public class Orders extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Calendar date;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "order_elements_sw")
	private Collection<OrderElement> orderElements;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private String status;
	
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