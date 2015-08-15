package hu.neuron.ier.business.vo;

import java.io.Serializable;

public class PurchasedOfferSwVO implements Serializable {

	private static final long serialVersionUID = 4294228205580255094L;

	private Long id;
	private OfferVO offerVO;
	private PurchaseVO purchaseVO;
	private int quanty;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OfferVO getOfferVO() {
		return offerVO;
	}

	public void setOfferVO(OfferVO offerVO) {
		this.offerVO = offerVO;
	}

	public PurchaseVO getPurchaseVO() {
		return purchaseVO;
	}

	public void setPurchaseVO(PurchaseVO purchaseVO) {
		this.purchaseVO = purchaseVO;
	}

	public int getQuanty() {
		return quanty;
	}

	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}

}
