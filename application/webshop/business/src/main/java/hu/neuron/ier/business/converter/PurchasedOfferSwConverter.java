package hu.neuron.ier.business.converter;

import hu.neuron.ier.business.vo.PurchasedOfferSwVO;
import hu.neuron.ier.core.entity.PurchasedOfferSw;

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
public class PurchasedOfferSwConverter {
	
	@Autowired
	@Qualifier("mapper")
	Mapper mapper;
	
	public PurchasedOfferSwVO toVO(PurchasedOfferSw PurchasedOfferSw) {
		if (PurchasedOfferSw == null) {
			return null;
		}
		return mapper.map(PurchasedOfferSw, PurchasedOfferSwVO.class);
	}

	public PurchasedOfferSw toEntity(PurchasedOfferSwVO PurchasedOfferSwVO) {
		if (PurchasedOfferSwVO == null) {
			return null;
		}
		return mapper.map(PurchasedOfferSwVO, PurchasedOfferSw.class);
	}

	public List<PurchasedOfferSwVO> toVO(List<PurchasedOfferSw> PurchasedOfferSwes) {
		if (PurchasedOfferSwes == null) {
			return null;
		}
		List<PurchasedOfferSwVO> vos = new ArrayList<PurchasedOfferSwVO>();
		for (PurchasedOfferSw PurchasedOfferSw : PurchasedOfferSwes) {
			vos.add(toVO(PurchasedOfferSw));
		}

		return vos;
	}

	public List<PurchasedOfferSw> toEntity(List<PurchasedOfferSwVO> vos) {
		if (vos == null) {
			return null;
		}
		List<PurchasedOfferSw> PurchasedOfferSwes = new ArrayList<PurchasedOfferSw>();
		for (PurchasedOfferSwVO vo : vos) {
			PurchasedOfferSwes.add(toEntity(vo));
		}

		return PurchasedOfferSwes;
	}
}
