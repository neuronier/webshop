package hu.neuron.ier.business.shoppingcart;

import hu.neuron.ier.business.vo.OfferVO;

public interface ShoppingCartRemote {

	void addOffer(Long ShoppingCartId, OfferVO offerVO) throws Exception;

	void deleteOffer(Long ShoppingCartId, Long offerId) throws Exception;

	void deleteAllOffer(Long ShoppingCartId) throws Exception;

}
