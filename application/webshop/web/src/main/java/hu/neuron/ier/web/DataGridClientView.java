package hu.neuron.ier.web;

import hu.neuron.ier.business.vo.OfferVO;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "dataGridClientView")
@ViewScoped
public class DataGridClientView implements Serializable {

	private static final long serialVersionUID = 32172141178229266L;

	private List<OfferVO> offers;

	private OfferVO selectedOffer;

	@ManagedProperty("#{offerClientController}")
	private OfferClientController service;

	@PostConstruct
	public void init() {
		offers = service.createClientOffers();
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

	public OfferClientController getService() {
		return service;
	}

	public void setService(OfferClientController service) {
		this.service = service;
	}

}