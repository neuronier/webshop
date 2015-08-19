package hu.neuron.ier.business.vo;

import java.io.Serializable;

import hu.neuron.ier.business.vo.ProductTypeVO;

public class OrderElementVO implements Serializable{

	/**
	 * 
	 */
	private Long id;
	private static final long serialVersionUID = 8506177194834764694L;
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
