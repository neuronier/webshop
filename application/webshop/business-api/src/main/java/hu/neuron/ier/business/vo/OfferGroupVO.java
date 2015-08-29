package hu.neuron.ier.business.vo;

import java.io.Serializable;

public class OfferGroupVO implements Serializable {

	private static final long serialVersionUID = 5644432609143683518L;

	private Long id;
	private String name;
	private String description;
	private Boolean active;
	private OfferGroupVO parentOfferGroup;

	public OfferGroupVO getParentOfferGroup() {
		return parentOfferGroup;
	}

	public void setParentOfferGroup(OfferGroupVO parentOfferGroup) {
		this.parentOfferGroup = parentOfferGroup;
	}

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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return name;
	}
	
	

}
