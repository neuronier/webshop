package hu.neuron.ier.core.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ShoppingCart")
public class ShoppingCart extends BaseEntity {
	
	private static final long serialVersionUID = 2948879647560008141L;
	
	@ManyToMany
	@JoinTable(name="shoppingcart_offer_sw")
	private List<Offer> offers;

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
}
