package hu.neuron.ier.business.offergroup;

import hu.neuron.ier.business.vo.OfferGroupVO;

public interface OfferGroupServiceRemote {

	void createOfferGroup(OfferGroupVO offerGroupVO) throws Exception;

	void deleteOfferGroup(Long id) throws Exception;

	void updateOfferGroupName(Long id, String name) throws Exception;

	void updateOfferGroupDescription(Long id, String description) throws Exception;
	// csoportokat egymás alá rendelni
	void offerGroupToOfferGroup(Long id, Long parentId) throws Exception;
	// hozzájuk ajánlatokat rendelni
	void offerToOfferGroup(Long offerId, Long offerGroupId) throws Exception;
	

}
