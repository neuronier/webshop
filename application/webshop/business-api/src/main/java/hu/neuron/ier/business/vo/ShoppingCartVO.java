package hu.neuron.ier.business.vo;

import java.io.Serializable;
import java.util.List;

public class ShoppingCartVO implements Serializable {

	private static final long serialVersionUID = -646811343516923416L;
	private List<OfferVO> offers;

	public List<OfferVO> getOffers() {
		return offers;
	}

	public void setOffers(List<OfferVO> offers) {
		this.offers = offers;
	}

}
