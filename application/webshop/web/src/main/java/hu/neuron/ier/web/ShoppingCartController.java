package hu.neuron.ier.web;

import hu.neuron.ier.business.offer.OfferServiceRemote;
import hu.neuron.ier.business.shoppingcart.ShoppingCartRemote;
import hu.neuron.ier.business.vo.OfferVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "shoppingCartController")
public class ShoppingCartController implements Serializable {

	private static final long serialVersionUID = -4897446152048179222L;

	@EJB(name = "ShoppingCartService", mappedName = "ShoppingCartService")
	ShoppingCartRemote shoppingCartService;
	@EJB(mappedName = "OfferService", name = "OfferService")
	OfferServiceRemote offerService;

	private List<OfferVO> offers = new ArrayList<OfferVO>();

	public void updateOffers() throws Exception {
		offers.addAll(offerService.getAllOffers());
	}

	public List<OfferVO> getOffers() throws Exception {
		this.updateOffers();
		return offers;
	}

	public void setOffers(List<OfferVO> offers) {
		this.offers = offers;
	}

	public void addOfferToShoppingCart(OfferVO offerVO) {
		
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

	public void deleteOfferFromShoppingCart(Long id) {
		OfferVO offerVO = findOfferInShoppingCart(id);
		offers.remove(offerVO);
	}

}
