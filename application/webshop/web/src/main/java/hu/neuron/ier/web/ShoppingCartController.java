package hu.neuron.ier.web;

import hu.neuron.ier.business.offer.OfferServiceRemote;
import hu.neuron.ier.business.shoppingcart.ShoppingCartRemote;
import hu.neuron.ier.business.vo.OfferVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "shoppingCartController")
public class ShoppingCartController implements Serializable {

	private static final long serialVersionUID = -4897446152048179222L;

	@EJB(name = "ShoppingCartService", mappedName = "ShoppingCartService")
	ShoppingCartRemote shoppingCartService;
	@EJB(mappedName = "OfferService", name = "OfferService")
	OfferServiceRemote offerService;

	private List<OfferVO> offers = new ArrayList<OfferVO>();

	public void addOfferToShoppingCart(OfferVO offerVO) {
		offers.add(offerVO);
	}

	public OfferVO findOfferInShoppingCart(Long id) {
		OfferVO offerVO = new OfferVO();
		for (OfferVO vo : offers) {
			if (vo.getId() == id) {
				offerVO = vo;
			}
		}
		return offerVO;
	}

	public void deleteOfferFromShoppingCart(Long selectedId) throws Exception {
		OfferVO offerVO = findOfferInShoppingCart(selectedId);
		offers.remove(offerVO);
	}

	public void deleteAllOfferFromShoppingCart() throws Exception {
		offers.removeAll(offers);
	}

	public List<OfferVO> getOffers() {
		return offers;
	}

	public void setOffers(List<OfferVO> offers) {
		this.offers = offers;
	}

}
