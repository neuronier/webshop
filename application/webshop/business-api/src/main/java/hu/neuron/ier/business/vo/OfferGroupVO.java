package hu.neuron.ier.business.vo;

import java.io.Serializable;

public class OfferGroupVO implements Serializable {

	private static final long serialVersionUID = 5644432609143683518L;

	private Long id;
	private String name;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
