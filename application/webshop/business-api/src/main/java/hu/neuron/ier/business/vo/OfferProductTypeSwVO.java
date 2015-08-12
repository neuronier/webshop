package hu.neuron.ier.business.vo;

import java.io.Serializable;

public class OfferProductTypeSwVO implements Serializable {

	private static final long serialVersionUID = 6776087320112591130L;
	private Long id;
	private OfferVO offer;
	private ProductTypeVO productType;
	private Integer quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OfferVO getOffer() {
		return offer;
	}

	public void setOffer(OfferVO offer) {
		this.offer = offer;
	}

	public ProductTypeVO getProductType() {
		return productType;
	}

	public void setProductType(ProductTypeVO productType) {
		this.productType = productType;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
