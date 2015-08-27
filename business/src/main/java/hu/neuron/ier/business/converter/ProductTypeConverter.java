package hu.neuron.ier.business.converter;

import hu.neuron.ier.business.vo.ProductTypeVO;
import hu.neuron.ier.core.entity.ProductType;

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
public class ProductTypeConverter {

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	public ProductTypeVO toVO(ProductType productType) {
		if (productType == null) {
			return null;
		}

		return mapper.map(productType, ProductTypeVO.class);
	}

	public ProductType toEntity(ProductTypeVO productTypeVO) {
		if (productTypeVO == null) {
			return null;
		}

		return mapper.map(productTypeVO, ProductType.class);
	}

	public List<ProductTypeVO> toVO(List<ProductType> productTypes) {
		if (productTypes == null) {
			return null;
		}
		List<ProductTypeVO> productTypeVOs = new ArrayList<ProductTypeVO>();
		for (ProductType productType : productTypes) {
			productTypeVOs.add(mapper.map(productType, ProductTypeVO.class));
		}

		return productTypeVOs;
	}

	public List<ProductType> toEntity(List<ProductTypeVO> productTypeVOs) {
		if (productTypeVOs == null) {
			return null;
		}
		List<ProductType> productTypes = new ArrayList<ProductType>();
		for (ProductTypeVO productTypeVO : productTypeVOs) {
			productTypes.add(mapper.map(productTypeVO, ProductType.class));
		}

		return productTypes;
	}
}
