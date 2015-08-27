package hu.neuron.ier.business.offergroup;

import hu.neuron.ier.business.vo.OfferGroupVO;

import java.util.List;

public interface OfferGroupServiceRemote {
	
	List<OfferGroupVO> findAllOfferGroup() throws Exception;

	OfferGroupVO createOfferGroup(OfferGroupVO offerGroupVO) throws Exception;

	void deleteOfferGroup(Long id) throws Exception;

	OfferGroupVO updateOfferGroupName(Long id, String name) throws Exception;

	OfferGroupVO updateOfferGroupDescription(Long id, String description) throws Exception;

	// csoportokat egymás alá rendelni
	OfferGroupVO offerGroupToOfferGroup(Long id, Long parentId) throws Exception;

	// hozzájuk ajánlatokat rendelni
	OfferGroupVO offerToOfferGroup(Long offerId, Long offerGroupId) throws Exception;
	
	//kulcsszó alapján keres a leírásban és a névben
	List<OfferGroupVO> searchOfferGroups(String key) throws Exception;
	
	List<OfferGroupVO> findAllParentOfferGroups() throws Exception;
	
	List<OfferGroupVO> findOfferGroupByParentOfferGroup(OfferGroupVO parentOfferGroup) throws Exception;
}
