package hu.neuron.ier.business.converter;

import hu.neuron.ier.business.vo.PurchaseVO;
import hu.neuron.ier.core.entity.Purchase;

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
public class PurchaseConverter {
	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	public PurchaseVO toVO(Purchase purchase) {
		if (purchase == null) {
			return null;
		}
		return mapper.map(purchase, PurchaseVO.class);
	}

	public Purchase toEntity(PurchaseVO purchaseVO) {
		if (purchaseVO == null) {
			return null;
		}
		return mapper.map(purchaseVO, Purchase.class);
	}

	public List<PurchaseVO> toVO(List<Purchase> purchases) {
		if (purchases == null) {
			return null;
		}
		List<PurchaseVO> vos = new ArrayList<PurchaseVO>();
		for (Purchase purchase : purchases) {
			vos.add(toVO(purchase));
		}
		return vos;
	}

	public List<Purchase> toEntity(List<PurchaseVO> purchaseVOs) {
		if (purchaseVOs == null) {
			return null;
		}
		List<Purchase> purchases = new ArrayList<Purchase>();
		for (PurchaseVO purchaseVO : purchaseVOs) {
			purchases.add(toEntity(purchaseVO));
		}
		return purchases;
	}
}
