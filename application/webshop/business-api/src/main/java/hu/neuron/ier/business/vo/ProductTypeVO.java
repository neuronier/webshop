package hu.neuron.ier.business.vo;

import java.io.Serializable;

public class ProductTypeVO implements Serializable {

	private static final long serialVersionUID = -8155487516508421880L;
	private Long id;
	private String itemNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
}
