package hu.neuron.ier.business.offer;

import hu.neuron.ier.business.converter.OfferConverter;
import hu.neuron.ier.business.user.UserServiceRemote;
import hu.neuron.ier.business.vo.OfferVO;
import hu.neuron.ier.business.vo.PurchaseVO;
import hu.neuron.ier.core.dao.OfferDao;
import hu.neuron.ier.core.dao.OfferGroupDao;
import hu.neuron.ier.core.entity.Offer;
import hu.neuron.ier.core.entity.PurchasedOfferSw;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
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
	UserServiceRemote offerService;

	@Autowired
	OfferGroupDao offerGroupDao;

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
		List<OfferVO> vos = converter
				.toVO(offerDao.findOfferByAction(isAction));

		return vos;
	}

	@Override
	public List<OfferVO> getFeaturedOffers(boolean isFeatured) throws Exception {
		List<OfferVO> vos = converter.toVO(offerDao
				.findOfferByFeatured(isFeatured));

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
	public OfferVO updateOfferDescription(Long id, String description)
			throws Exception {
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

	@Override
	public List<OfferVO> getOffersByParentOfferGroup(Long parentId)
			throws Exception {
		List<OfferVO> offers = converter.toVO(offerDao
				.findOfferByParentOfferGroup(offerGroupDao.findOne(parentId)));
		return offers;
	}

	@Override
	public OfferVO getOfferById(Long id) throws Exception {
		
		return converter.toVO(offerDao.findOne(id));
	}
	
	@Override
	public List<OfferVO> getOfferList() throws Exception {
		List<OfferVO> vos = converter.toVO(offerDao.findAll());

		return vos;
	}
	
	@Override
	public List<OfferVO> getOfferList(int page, int pageSize, String sortField, int dir,
			String filter, String filterColumnName) throws Exception {

		Direction direction = dir == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, pageSize, new Sort(new Order(direction,
				sortField)));
		List<OfferVO> vos = new ArrayList<OfferVO>(pageSize);
		Page<Offer> entities;

		if (filter.length() != 0 && filterColumnName.equals("name")) {
			entities = offerDao.findByNameStartsWith(filter, pageRequest);
		} else {
			entities = offerDao.findAll(pageRequest);
		}

		if (entities != null && entities.getSize() > 0) {
			List<Offer> contents = entities.getContent();
			for (Offer offer : contents) {
				vos.add(converter.toVO(offer));
			}
		}
		return vos;
	}
	
	@Override
	public int getRowNumber() throws Exception {

		return (int) offerDao.count();
	}
	
	@Override
	public OfferVO saveOffer(OfferVO selectedOffer) throws Exception {
		OfferVO vo = converter.toVO(offerDao.save(converter.toEntity(selectedOffer)));
		return vo;
	}
	
	@Override
	public List<OfferVO> getOffersFromPurchase(PurchaseVO purchaseVO) {
		List<OfferVO> offerVO = new ArrayList<OfferVO>();
		try {
			
			
				offerVO=converter.toVO(offerDao.getOffersFromPurchase(purchaseVO.getId())) ;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return offerVO;
	}

}
