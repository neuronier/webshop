package hu.neuron.ier.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The Class of Offer.
 */
@Entity
@Table(name = "Offer")
public class Offer extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Long itemNumber;
	private Long cost;
	private String name;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(Long itemNumber) {
		this.itemNumber = itemNumber;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

}
