package hu.neuron.ier.web;

import hu.neuron.ier.business.vo.OfferVO;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@SessionScoped
@ManagedBean(name="offerSessionBean")
public class OfferSessionBean {
	private List<OfferVO> offers;

	public List<OfferVO> getOffers() {
		return offers;
	}

	public void setOffers(List<OfferVO> offers) {
		this.offers = offers;
	}
	

}
