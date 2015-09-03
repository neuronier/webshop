package hu.neuron.ier.business.vo;



import java.io.Serializable;

public class OfferVO implements Serializable {

	private static final long serialVersionUID = -2055035515774259767L;
	private Long id;
	private Long newCost;
	private Long originalCost;
	private boolean featured;
	private boolean action;
	private String name;
	private String description;

	private OfferGroupVO parentOfferGroup;

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

	public OfferGroupVO getParentOfferGroup() {
		return parentOfferGroup;
	}

	public void setParentOfferGroup(OfferGroupVO parentOfferGroup) {
		this.parentOfferGroup = parentOfferGroup;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfferVO other = (OfferVO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}
	

}
