package hu.neuron.ier.core.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Item")
public class Item extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1746822095658393758L;
	@OneToOne
	private ProductType productType;
	private int quanty;
	
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	public int getQuanty() {
		return quanty;
	}
	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}


}
