package hu.neuron.ier.core.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OfferProductTypeSw")
public class OfferProductTypeSw extends BaseEntity {

	private static final long serialVersionUID = 6877956140719577667L;
	@ManyToOne
	private Offer offer;
	@ManyToOne
	private ProductType productType;
	private Integer quantity;

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
