package hu.neuron.ier.business.offergroup;

import hu.neuron.ier.business.converter.OfferGroupConverter;
import hu.neuron.ier.business.vo.OfferGroupVO;
import hu.neuron.ier.core.dao.OfferDao;
import hu.neuron.ier.core.dao.OfferGroupDao;
import hu.neuron.ier.core.entity.Offer;
import hu.neuron.ier.core.entity.OfferGroup;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "OfferGroupService", name = "OfferGroupService")
@Remote(OfferGroupServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class OfferGroupServiceImpl implements OfferGroupServiceRemote, Serializable {

	private static final long serialVersionUID = -5417875507641978550L;

	@Autowired
	OfferGroupDao offerGroupDao;

	@Autowired
	OfferDao offerDao;

	@EJB
	OfferGroupConverter converter;

	@Override
	public void createOfferGroup(OfferGroupVO offerGroupVO) throws Exception {
		offerGroupDao.save(converter.toEntity(offerGroupVO));
	}

	@Override
	public void deleteOfferGroup(Long id) throws Exception {
		offerGroupDao.delete(id);
	}

	@Override
	public void updateOfferGroupName(Long id, String name) throws Exception {
		OfferGroup og = offerGroupDao.findOne(id);
		og.setName(name);
		offerGroupDao.save(og);
	}

	@Override
	public void updateOfferGroupDescription(Long id, String description) throws Exception {
		OfferGroup og = offerGroupDao.findOne(id);
		og.setDescription(description);
		offerGroupDao.save(og);
	}

	@Override
	public void offerGroupToOfferGroup(Long id, Long parentId) throws Exception {
		OfferGroup parent = offerGroupDao.findOne(parentId);
		OfferGroup og = offerGroupDao.findOne(id);
		og.setParentOfferGroup(parent);
		offerGroupDao.save(og);
	}

	@Override
	public void offerToOfferGroup(Long offerId, Long offerGroupId) throws Exception {

		Offer offer = offerDao.findOne(offerId);
		OfferGroup og = offerGroupDao.findOne(offerGroupId);
		offer.setParentOfferGroup(og);
		offerDao.save(offer);
	}

}
