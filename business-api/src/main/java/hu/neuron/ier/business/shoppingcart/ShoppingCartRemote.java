package hu.neuron.ier.business.shoppingcart;

import hu.neuron.ier.business.vo.OfferVO;
import hu.neuron.ier.business.vo.ShoppingCartVO;

public interface ShoppingCartRemote {

	ShoppingCartVO createShoppingCart(ShoppingCartVO shoppingCartVO) throws Exception;

	ShoppingCartVO addOffer(Long ShoppingCartId, OfferVO offerVO) throws Exception;

	void deleteOffer(Long ShoppingCartId, Long offerId) throws Exception;

	void deleteAllOffer(Long ShoppingCartId) throws Exception;

}
