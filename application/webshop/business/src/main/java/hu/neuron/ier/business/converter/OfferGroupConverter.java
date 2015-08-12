package hu.neuron.ier.business.converter;

import hu.neuron.ier.business.vo.OfferGroupVO;
import hu.neuron.ier.core.entity.OfferGroup;

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
public class OfferGroupConverter {

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	public OfferGroupVO toVO(OfferGroup offerGroup) {

		if (offerGroup == null) {
			return null;
		}

		return mapper.map(offerGroup, OfferGroupVO.class);
	}

	public OfferGroup toEntity(OfferGroupVO offerGroupVO) {

		if (offerGroupVO == null) {
			return null;
		}

		return mapper.map(offerGroupVO, OfferGroup.class);
	}

	public List<OfferGroupVO> toVO(List<OfferGroup> offerGroups) {

		if (offerGroups == null) {
			return null;
		}

		List<OfferGroupVO> vos = new ArrayList<>();
		for (OfferGroup offerGroup : offerGroups) {
			vos.add(toVO(offerGroup));
		}

		return vos;
	}

	public List<OfferGroup> toEntity(List<OfferGroupVO> vos) {

		if (vos == null) {
			return null;
		}

		List<OfferGroup> offerGroups = new ArrayList<OfferGroup>();
		for (OfferGroupVO vo : vos) {
			offerGroups.add(toEntity(vo));
		}
		return offerGroups;
	}

}
