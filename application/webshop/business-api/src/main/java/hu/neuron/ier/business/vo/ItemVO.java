package hu.neuron.ier.business.vo;


import java.io.Serializable;

import javax.jws.Oneway;

public class ItemVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3607840405822048423L;

	private Long id;
	private ProductTypeVO productType;
	private int quanty;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ProductTypeVO getProductType() {
		return productType;
	}
	public void setProductType(ProductTypeVO productType) {
		this.productType = productType;
	}
	public int getQuanty() {
		return quanty;
	}
	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}
	
	
}
