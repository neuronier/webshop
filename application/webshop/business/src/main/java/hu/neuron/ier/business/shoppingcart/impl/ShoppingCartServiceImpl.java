package hu.neuron.ier.business.shoppingcart.impl;

import hu.neuron.ier.business.converter.OfferConverter;
import hu.neuron.ier.business.converter.ShoppingCartConverter;
import hu.neuron.ier.business.shoppingcart.ShoppingCartRemote;
import hu.neuron.ier.business.vo.OfferVO;
import hu.neuron.ier.core.dao.OfferDao;
import hu.neuron.ier.core.dao.ShoppingCartDao;
import hu.neuron.ier.core.entity.Offer;
import hu.neuron.ier.core.entity.ShoppingCart;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;

import org.springframework.beans.factory.annotation.Autowired;

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
	public void addOffer(Long ShoppingCartId, OfferVO offerVO) throws Exception {
		ShoppingCart cart = shoppingCartDao.findOne(ShoppingCartId);
		cart.getOffers().add(offerConverter.toEntity(offerVO));
	}

	@Override
	public void deleteOffer(Long ShoppingCartId, Long offerId) throws Exception {
		ShoppingCart cart = shoppingCartDao.findOne(ShoppingCartId);
		Offer offer = offerDao.findOne(offerId);
		cart.getOffers().remove(offer);

	}

	@Override
	public void deleteAllOffer(Long ShoppingCartId) throws Exception {
		ShoppingCart cart = shoppingCartDao.findOne(ShoppingCartId);
		List<Offer> offers = offerDao.findAll();
		cart.getOffers().removeAll(offers);
	}

}
