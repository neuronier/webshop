package hu.neuron.ier.business.shoppingcart.impl;

import hu.neuron.ier.business.converter.OfferConverter;
import hu.neuron.ier.business.converter.ShoppingCartConverter;
import hu.neuron.ier.business.shoppingcart.ShoppingCartRemote;
import hu.neuron.ier.business.vo.OfferVO;
import hu.neuron.ier.business.vo.ShoppingCartVO;
import hu.neuron.ier.core.dao.OfferDao;
import hu.neuron.ier.core.dao.ShoppingCartDao;
import hu.neuron.ier.core.entity.Offer;
import hu.neuron.ier.core.entity.ShoppingCart;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "ShoppingCartService", name = "ShoppingCartService")
@Remote(ShoppingCartRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class ShoppingCartServiceImpl implements ShoppingCartRemote, Serializable {

	private static final long serialVersionUID = -6887318215021194006L;

	@Autowired
	ShoppingCartDao shoppingCartDao;
	@Autowired
	OfferDao offerDao;

	@EJB
	ShoppingCartConverter shoppingCartConverter;
	@EJB
	OfferConverter offerConverter;

	@Override
	public ShoppingCartVO addOffer(Long ShoppingCartId, OfferVO offerVO) throws Exception {
		ShoppingCart cart = shoppingCartDao.findOne(ShoppingCartId);
		cart.getOffers().add(offerConverter.toEntity(offerVO));
		cart = shoppingCartDao.save(cart);
		return shoppingCartConverter.toVO(cart);
	}

	@Override
	public void deleteOffer(Long ShoppingCartId, Long offerId) throws Exception {
		ShoppingCart cart = shoppingCartDao.findOne(ShoppingCartId);
		Offer offer = offerDao.findOne(offerId);
		List<Offer> offers = cart.getOffers();
		offers.remove(offer);
		shoppingCartDao.save(cart);
	}

	@Override
	public void deleteAllOffer(Long ShoppingCartId) throws Exception {
		ShoppingCart cart = shoppingCartDao.findOne(ShoppingCartId);
		cart.getOffers().removeAll(cart.getOffers());
		shoppingCartDao.save(cart);
	}

	@Override
	public ShoppingCartVO createShoppingCart(ShoppingCartVO shoppingCartVO) throws Exception {
		ShoppingCartVO cartVO = shoppingCartConverter.toVO(shoppingCartDao
				.save(shoppingCartConverter.toEntity(shoppingCartVO)));
		return cartVO;
	}

}
