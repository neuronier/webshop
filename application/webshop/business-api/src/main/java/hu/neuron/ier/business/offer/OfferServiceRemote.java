package hu.neuron.ier.business.offer;

import hu.neuron.ier.business.vo.OfferVO;

import java.util.List;

public interface OfferServiceRemote {

	OfferVO createOffer(OfferVO offerVO) throws Exception;

	void deleteOffer(Long id) throws Exception;

	List<OfferVO> getAllOffers() throws Exception;

	List<OfferVO> getActionOffers(boolean isAction) throws Exception;

	List<OfferVO> getFeaturedOffers(boolean isFeatured) throws Exception;

	List<OfferVO> getOffersByName(String name) throws Exception;

	OfferVO updateOfferCost(Long id, Long cost) throws Exception;

	OfferVO updateOfferDescription(Long id, String description) throws Exception;

	OfferVO updateOfferName(Long id, String name) throws Exception;

}
