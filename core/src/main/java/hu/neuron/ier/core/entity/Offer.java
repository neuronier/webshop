package hu.neuron.ier.core.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class of Offer.
 */
@Entity
@Table(name = "Offer")
// @NamedQuery(name = "Offer.findOfferByID", query =
// "SELECT o FROM Offer o  WHERE o.id = :id")
public class Offer extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Long newCost;
	private Long originalCost;
	private boolean featured;
	private boolean action;
	private String name;
	private String description;

	@ManyToOne
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

	public Long getNewCost() {
		return newCost;
	}

	public void setNewCost(Long newCost) {
		this.newCost = newCost;
	}

	public Long getOriginalCost() {
		return originalCost;
	}

	public void setOriginalCost(Long originalCost) {
		this.originalCost = originalCost;
	}

	public boolean isFeatured() {
		return featured;
	}

	public void setFeatured(boolean featured) {
		this.featured = featured;
	}

	public boolean isAction() {
		return action;
	}

	public void setAction(boolean action) {
		this.action = action;
	}

	public OfferGroup getParentOfferGroup() {
		return parentOfferGroup;
	}

	public void setParentOfferGroup(OfferGroup parentOfferGroup) {
		this.parentOfferGroup = parentOfferGroup;
	}

}
