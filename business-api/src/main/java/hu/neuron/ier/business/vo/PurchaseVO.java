package hu.neuron.ier.business.vo;

import java.io.Serializable;
import java.util.Calendar;

public class PurchaseVO implements Serializable {

	private static final long serialVersionUID = 4139648747904199370L;

	private Long id;
	private ClientVO clientVO;
	private Calendar date;
	private Long fullCost;
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClientVO getClientVO() {
		return clientVO;
	}

	public void setClientVO(ClientVO clientVO) {
		this.clientVO = clientVO;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Long getFullCost() {
		return fullCost;
	}

	public void setFullCost(Long fullCost) {
		this.fullCost = fullCost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
