package hu.neuron.ier.business.offergroup;

import hu.neuron.ier.business.vo.OfferGroupVO;

public interface OfferGroupServiceRemote {

	void createOfferGroup(OfferGroupVO offerGroupVO) throws Exception;

	void deleteOfferGroup(Long id) throws Exception;

	void updateOfferGroupName(String name) throws Exception;

	void updateOfferGroupDescription(String description) throws Exception;

	// csoportokat egymás alá rendelni
	// hozzájuk ajánlatokat rendelni

}
