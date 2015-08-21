package hu.neuron.ier.business.producttype.impl;

import hu.neuron.ier.business.converter.ProductTypeConverter;
import hu.neuron.ier.business.producttype.ProductTypeServiceRemote;
import hu.neuron.ier.business.vo.ProductTypeVO;
import hu.neuron.ier.core.dao.ProductTypeDao;

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

@Stateless(mappedName = "ProductTypeService", name = "ProductTypeService")
@Remote(ProductTypeServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class ProductTypeServiceImpl implements ProductTypeServiceRemote, Serializable {

	private static final long serialVersionUID = -2484088061415451266L;

	@Autowired
	ProductTypeDao productTypeDao;

	@EJB
	ProductTypeConverter productTypeConverter;

	@Override
	public ProductTypeVO createProductType(ProductTypeVO productTypeVO) throws Exception {
		ProductTypeVO vo = productTypeConverter.toVO(productTypeDao.save(productTypeConverter
				.toEntity(productTypeVO)));
		return vo;
	}

	@Override
	public void deleteProductType(Long id) throws Exception {
		productTypeDao.delete(id);
	}

	@Override
	public ProductTypeVO getProductTypeByItemNumber(String itemNumber) throws Exception {
		ProductTypeVO vo = productTypeConverter.toVO(productTypeDao
				.findProductTypeByItemNumber(itemNumber));
		return vo;
	}

	@Override
	public List<ProductTypeVO> getAllProductType() throws Exception {
		List<ProductTypeVO> vos = productTypeConverter.toVO(productTypeDao.findAll());
		return vos;
	}

}
