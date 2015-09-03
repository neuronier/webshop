package hu.neuron.ier.core.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Purchase")
@NamedQuery(name = "findByClient", query = "Select p from Purchase p where p.client = :client")
public class Purchase extends BaseEntity {

	private static final long serialVersionUID = 1826068613871566627L;
	@ManyToOne
	private Client client;
	//lehet Date kell majd
	private Date date;
	private long fullCost;
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

	public long getFullCost() {
		return fullCost;
	}

	public void setFullCost(long fullCost) {
		this.fullCost = fullCost;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
