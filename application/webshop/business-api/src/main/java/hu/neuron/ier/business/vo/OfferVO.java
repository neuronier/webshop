package hu.neuron.ier.business.vo;

import java.io.Serializable;

public class OfferVO implements Serializable {

	private static final long serialVersionUID = -2055035515774259767L;
	private Long id;
	private Long cost;
	private String name;
	private String description;
	private OfferVO parentOffer;
	private OfferGroupVO parentOfferGroup;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
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

	public OfferVO getParentOffer() {
		return parentOffer;
	}

	public void setParentOffer(OfferVO parentOffer) {
		this.parentOffer = parentOffer;
	}

	public OfferGroupVO getParentOfferGroup() {
		return parentOfferGroup;
	}

	public void setParentOfferGroup(OfferGroupVO parentOfferGroup) {
		this.parentOfferGroup = parentOfferGroup;
	}

}
