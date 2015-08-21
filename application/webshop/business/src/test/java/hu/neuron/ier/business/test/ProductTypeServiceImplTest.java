package hu.neuron.ier.business.test;

import hu.neuron.ier.business.producttype.ProductTypeServiceRemote;
import hu.neuron.ier.business.vo.ProductTypeVO;

import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductTypeServiceImplTest {

	private static ProductTypeVO productTypeVO;

	@EJB(mappedName = "ProductTypeService", name = "ProductTypeService")
	ProductTypeServiceRemote productTypeService;

	@Before
	public void startTheContainer() throws Exception {
		final Properties p = new Properties();

		p.put("hu.neuron.ier.jpa.hibernate.hbm2ddl.auto", "create");
		p.put("hu.neuron.ier.jpa.hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		p.put("hu.neuron.ier.database.test", "new://Resource?type=DataSource");
		p.put("hu.neuron.ier.database.test.JtaManaged", "false");
		p.put("hu.neuron.ier.database.test.JdbcDriver", "org.hsqldb.jdbcDriver");
		p.put("hu.neuron.ier.database.test.JdbcUrl", "jdbc:hsqldb:mem:aname");

		EJBContainer ejbContainer = EJBContainer.createEJBContainer(p);
		ejbContainer.getContext().bind("inject", this);
	}

	@Test
	public void test1createProductType() {
		try {
			productTypeVO = new ProductTypeVO();
			productTypeVO.setItemNumber("123");
			productTypeVO = productTypeService.createProductType(productTypeVO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test2getProductTypeByItemNumber() {
		try {
			ProductTypeVO productTypeVO = productTypeService
					.getProductTypeByItemNumber(this.productTypeVO.getItemNumber());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3getAllProductType() {
		try {
			List<ProductTypeVO> vos = productTypeService.getAllProductType();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test4deleteProductType() {
		try {
			productTypeService.deleteProductType(productTypeVO.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
