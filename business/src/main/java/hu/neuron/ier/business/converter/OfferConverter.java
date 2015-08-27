package hu.neuron.ier.business.converter;

import hu.neuron.ier.business.vo.OfferVO;
import hu.neuron.ier.core.entity.Offer;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.interceptor.Interceptors;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Singleton
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class OfferConverter {

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	public OfferVO toVO(Offer offer) {
		if (offer == null) {
			return null;
		}
		return mapper.map(offer, OfferVO.class);
	}

	public Offer toEntity(OfferVO offerVO) {
		if (offerVO == null) {
			return null;
		}

		return mapper.map(offerVO, Offer.class);

	}

	public List<OfferVO> toVO(List<Offer> offers) {
		if (offers == null) {
			return null;
		}

		List<OfferVO> offerVOs = new ArrayList<OfferVO>();
		for (Offer offer : offers) {
			offerVOs.add(toVO(offer));
		}

		return offerVOs;
	}

	public List<Offer> toEntity(List<OfferVO> vos) {
		if (vos == null) {
			return null;
		}

		List<Offer> offers = new ArrayList<Offer>();
		for (OfferVO offerVO : vos) {
			offers.add(toEntity(offerVO));
		}

		return offers;
	}
}
