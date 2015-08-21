package hu.neuron.ier.business.test;

import hu.neuron.ier.business.orderelement.OrderElementServiceRemote;
import hu.neuron.ier.business.producttype.ProductTypeServiceRemote;
import hu.neuron.ier.business.vo.OrderElementVO;
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
public class OrderElementServiceImplTest {

	private static OrderElementVO orderElementVO;
	private static ProductTypeVO productTypeVO;
	private EJBContainer ejbContainer;


	@EJB(mappedName = "OrderElementService", name = "OrderElementService")
	OrderElementServiceRemote orderElementService;
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

		ejbContainer = EJBContainer.createEJBContainer(p);
		ejbContainer.getContext().bind("inject", this);
	}

	@Test
	public void test1createOrderElement() {
		try {
			productTypeVO = new ProductTypeVO();
			productTypeVO = productTypeService.createProductType(productTypeVO);

			orderElementVO = new OrderElementVO();
			orderElementVO.setQuanty(10);
			orderElementVO.setProductType(productTypeVO);
			orderElementVO = orderElementService.createOrderElement(orderElementVO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test2updateOrderElementQuanty() {
		try {
			orderElementVO = orderElementService.updateOrderElementQuanty(orderElementVO.getId(),
					15);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3getAllOrderElement() {
		try {
			List<OrderElementVO> elementVOs = orderElementService.getAllOrderElement();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test4deleteOrderElement() {
		try {
			orderElementService.deleteOrderElement(orderElementVO.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void test99closeEJBContainer() {
		ejbContainer.close();
	}

}
