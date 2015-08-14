package hu.neuron.ier.core.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Order")
@NamedQuery(name = "findByClient", query = "Select o from Order o where o.client = :client")
public class Order extends BaseEntity {

	private static final long serialVersionUID = 1826068613871566627L;
	@ManyToOne
	private Client client;
	private Calendar date;
	private Double fullCost;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Double getFullCost() {
		return fullCost;
	}

	public void setFullCost(Double fullCost) {
		this.fullCost = fullCost;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
