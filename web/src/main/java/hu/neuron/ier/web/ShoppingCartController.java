package hu.neuron.ier.web;

import hu.neuron.ier.business.client.ClientSelfCareServiceRemote;
import hu.neuron.ier.business.offer.OfferServiceRemote;
import hu.neuron.ier.business.shoppingcart.ShoppingCartRemote;
import hu.neuron.ier.business.vo.ClientVO;
import hu.neuron.ier.business.vo.OfferVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.core.context.SecurityContextHolder;

@SessionScoped
@ManagedBean(name = "shoppingCartController")
public class ShoppingCartController implements Serializable {

	private static final long serialVersionUID = -4897446152048179222L;

	private ClientVO currentClient;

	@EJB(name = "ShoppingCartService", mappedName = "ShoppingCartService")
	private ShoppingCartRemote shoppingCartService;
	@EJB(mappedName = "OfferService", name = "OfferService")
	private OfferServiceRemote offerService;
	@EJB(name = "ClientSelfCareService", mappedName = "ClientSelfCareService")
	private ClientSelfCareServiceRemote clientSelfCareService;

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

	public int offerCounter(OfferVO offerVO) {
		int count = 0;
		for (OfferVO offer : offers) {
			if (offer.getId() == offerVO.getId()) {
				count++;
			}
		}

		return count;
	}

	public void info(OfferVO offerVO) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, offerVO.getName(),
						"a kosárba helyezve."));
	}

	public void shoppingCartToClient() {
		currentClient = new ClientVO();
		currentClient = clientSelfCareService.getClientByName(SecurityContextHolder.getContext()
				.getAuthentication().getName());
		currentClient.getShoppingCart().getOffers().addAll(offers);
	}

	public List<OfferVO> getOffers() {
		return offers;
	}

	public void setOffers(List<OfferVO> offers) {
		this.offers = offers;
	}

}