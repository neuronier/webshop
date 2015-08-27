package hu.neuron.ier.core.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PurchasedOfferSw")
public class PurchasedOfferSw extends BaseEntity {

	private static final long serialVersionUID = 2562692275986465726L;
	@ManyToOne
	private Offer offer;
	@ManyToOne
	private Purchase purchase;
	private int quanty;

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public int getQuanty() {
		return quanty;
	}

	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}

}
