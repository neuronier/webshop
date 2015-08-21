package hu.neuron.ier.core.dao.test;

import hu.neuron.ier.core.dao.OrderElementDao;
import hu.neuron.ier.core.dao.ProductTypeDao;
import hu.neuron.ier.core.entity.OrderElement;
import hu.neuron.ier.core.entity.ProductType;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-test-core.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class OrderElementDaoTest {

	private static final Logger logger = Logger.getLogger(OrderElementDaoTest.class);

	private static OrderElement orderElement;
	private static ProductType productType;

	@Autowired
	OrderElementDao orderElementDao;
	@Autowired
	ProductTypeDao productTypeDao;

	@Test
	public void test1save() {
		try {
			productType = new ProductType();
			productType = productTypeDao.save(productType);
			orderElement = new OrderElement();
			orderElement.setQuanty(1);
			orderElement.setProductType(productType);
			orderElement = orderElementDao.save(orderElement);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test2findOrderElementByProductType() {
		try {
			List<OrderElement> orderElements = orderElementDao
					.findOrderElementByProductType(this.orderElement.getProductType());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3findAll() {
		try {
			List<OrderElement> orderElements = orderElementDao.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test4delete() {
		try {
			orderElementDao.delete(orderElement.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
