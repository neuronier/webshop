package hu.neuron.ier.business.offergroup;

import hu.neuron.ier.business.vo.OfferGroupVO;

public interface OfferGroupServiceRemote {

	OfferGroupVO createOfferGroup(OfferGroupVO offerGroupVO) throws Exception;

	void deleteOfferGroup(Long id) throws Exception;

	OfferGroupVO updateOfferGroupName(Long id, String name) throws Exception;

	OfferGroupVO updateOfferGroupDescription(Long id, String description) throws Exception;

	// csoportokat egymás alá rendelni
	OfferGroupVO offerGroupToOfferGroup(Long id, Long parentId) throws Exception;

	// hozzájuk ajánlatokat rendelni
	OfferGroupVO offerToOfferGroup(Long offerId, Long offerGroupId) throws Exception;

}
