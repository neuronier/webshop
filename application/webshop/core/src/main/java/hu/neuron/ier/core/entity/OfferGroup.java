package hu.neuron.ier.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The Class of OfferGroup.
 */
@Entity
@Table(name = "OfferGroup")
public class OfferGroup extends BaseEntity {

	private static final long serialVersionUID = 1L;

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

}
