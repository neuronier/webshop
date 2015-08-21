package hu.neuron.ier.business.offer;

import hu.neuron.ier.business.converter.OfferConverter;
import hu.neuron.ier.business.vo.OfferVO;
import hu.neuron.ier.core.dao.OfferDao;

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

@Stateless(mappedName = "OfferService", name = "OfferService")
@Remote(OfferServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class OfferServiceImpl implements OfferServiceRemote, Serializable {

	private static final long serialVersionUID = 495908373096665339L;

	@Autowired
	OfferDao offerDao;

	@EJB
	OfferConverter converter;

	@Override
	public OfferVO createOffer(OfferVO offerVO) throws Exception {
		OfferVO vo = converter.toVO(offerDao.save(converter.toEntity(offerVO)));
		return vo;

	}

	@Override
	public void deleteOffer(Long id) throws Exception {
		offerDao.delete(id);

	}

	@Override
	public List<OfferVO> getAllOffers() throws Exception {
		List<OfferVO> vos = converter.toVO(offerDao.findAll());

		return vos;
	}

	@Override
	public List<OfferVO> getActionOffers(boolean isAction) throws Exception {
		List<OfferVO> vos = converter.toVO(offerDao.findOfferByAction(isAction));

		return vos;
	}

	@Override
	public List<OfferVO> getFeaturedOffers(boolean isFeatured) throws Exception {
		List<OfferVO> vos = converter.toVO(offerDao.findOfferByFeatured(isFeatured));

		return vos;
	}

	@Override
	public List<OfferVO> getOffersByName(String name) throws Exception {
		List<OfferVO> vos = converter.toVO(offerDao.findOfferByName(name));

		return vos;
	}

	@Override
	public OfferVO updateOfferCost(Long id, Long cost) throws Exception {
		OfferVO offerVO = converter.toVO(offerDao.findOne(id));
		offerVO.setNewCost(cost);
		createOffer(offerVO);
		return offerVO;
	}

	@Override
	public OfferVO updateOfferDescription(Long id, String description) throws Exception {
		OfferVO offerVO = converter.toVO(offerDao.findOne(id));
		offerVO.setDescription(description);
		createOffer(offerVO);
		return offerVO;
	}

	@Override
	public OfferVO updateOfferName(Long id, String name) throws Exception {
		OfferVO offerVO = converter.toVO(offerDao.findOne(id));
		offerVO.setName(name);
		createOffer(offerVO);
		return offerVO;

	}
	
	@Override
	public List<OfferVO> searchOffers(String key) throws Exception {
		return converter.toVO(offerDao.searchOffer(key));
	}

}
