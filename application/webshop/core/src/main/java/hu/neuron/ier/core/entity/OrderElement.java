package hu.neuron.ier.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The Class of OrderElement.
 */
@Entity
@Table(name = "OrderElement")
public class OrderElement extends BaseEntity{

	private static final long serialVersionUID = 6410356804561545056L;
	
	private ProductType productType;
	private int quanty;

	public int getQuanty() {
		return quanty;
	}

	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	
	
}
