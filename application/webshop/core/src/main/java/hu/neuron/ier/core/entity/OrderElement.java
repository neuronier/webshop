package hu.neuron.ier.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The Class of OrderElement.
 */
@Entity
@Table(name = "OrderElement")
public class OrderElement extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6410356804561545056L;
	
	private String itemNumber;
	private int quanty;

	public int getQuanty() {
		return quanty;
	}

	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
	
}
