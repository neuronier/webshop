package hu.neuron.ier.core.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="OrderedOfferSw")
public class OrderedOfferSw extends BaseEntity{

	private static final long serialVersionUID = 2562692275986465726L;
	@ManyToOne
	private Offer offer;
	@ManyToOne
	private Purchase order;
	public Offer getOffer() {
		return offer;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	public Purchase getOrder() {
		return order;
	}
	public void setOrder(Purchase order) {
		this.order = order;
	}
	private int quanty;
	
	public int getQuanty() {
		return quanty;
	}
	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}

}
