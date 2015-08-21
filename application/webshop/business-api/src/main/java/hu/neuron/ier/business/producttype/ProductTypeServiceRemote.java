package hu.neuron.ier.business.producttype;

import hu.neuron.ier.business.vo.ProductTypeVO;

import java.util.List;

public interface ProductTypeServiceRemote {

	ProductTypeVO createProductType(ProductTypeVO productTypeVO) throws Exception;

	void deleteProductType(Long id) throws Exception;

	ProductTypeVO getProductTypeByItemNumber(String itemNumber) throws Exception;

	List<ProductTypeVO> getAllProductType() throws Exception;

}
