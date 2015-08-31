package hu.neuron.ier.web;

import hu.neuron.ier.business.vo.OfferVO;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "dataGridPurchaseView")
@ViewScoped
public class DataGridPurchaseView implements Serializable {

	private static final long serialVersionUID = 32172141178229266L;

	private List<OfferVO> offers;
	private Long purchaseId;
	private OfferVO selectedOffer;

	@ManagedProperty("#{offerPurchaseController}")
	private OfferPurchaseController service;

	@PostConstruct
	public void init() {
		offers = service.createPurchasedOffers(purchaseId);
	}

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public List<OfferVO> getOffers() {
		return offers;
	}

	public void setOffers(List<OfferVO> offers) {
		this.offers = offers;
	}

	public OfferVO getSelectedOffer() {
		return selectedOffer;
	}

	public void setSelectedOffer(OfferVO selectedOffer) {
		this.selectedOffer = selectedOffer;
	}

	public OfferPurchaseController getService() {
		return service;
	}

	public void setService(OfferPurchaseController service) {
		this.service = service;
	}

}