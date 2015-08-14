package hu.neuron.ier.core.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Order")
public class Order extends BaseEntity{

	private static final long serialVersionUID = 1826068613871566627L;
	@ManyToOne(fetch = FetchType.LAZY)
	private Client client;
	private Date date;
	private Double fullCost;
	private String status;
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
