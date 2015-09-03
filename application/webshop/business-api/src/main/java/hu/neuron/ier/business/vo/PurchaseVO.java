package hu.neuron.ier.business.vo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class PurchaseVO implements Serializable {

	private static final long serialVersionUID = 4139648747904199370L;

	private Long id;
	private ClientVO client;
	private Date date;
	private long fullCost;
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ClientVO getClient() {
		return client;
	}

	public void setClient(ClientVO client) {
		this.client = client;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getFullCost() {
		return fullCost;
	}

	public void setFullCost(long fullCost) {
		this.fullCost = fullCost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
