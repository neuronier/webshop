package hu.neuron.ier.core.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The Class of Offer.
 */
@Entity
@Table(name = "Offer")
@NamedQuery(name = "Offer.findOfferByID", query = "SELECT o FROM Offer o  WHERE o.id = :id")
public class Offer extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Long cost;
	private String name;
	private String description;
	private Offer parentOffer;
	private OfferGroup parentOfferGroup;

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

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public Offer getParentOffer() {
		return parentOffer;
	}

	public void setParentOffer(Offer parentOffer) {
		this.parentOffer = parentOffer;
	}

	public OfferGroup getParentOfferGroup() {
		return parentOfferGroup;
	}

	public void setParentOfferGroup(OfferGroup parentOfferGroup) {
		this.parentOfferGroup = parentOfferGroup;
	}

}
