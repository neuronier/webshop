package hu.neuron.ier.core.dao.test;

import hu.neuron.ier.core.dao.ProductTypeDao;
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
public class ProductTypeDaoTest {

	private static final Logger logger = Logger.getLogger(ProductTypeDaoTest.class);

	private static ProductType productType;

	@Autowired
	ProductTypeDao productTypeDao;

	@Test
	public void test1Save() {
		try {
			productType = new ProductType();
			productType.setId(1l);
			productType.setItemNumber("1234asdf");
			productType = productTypeDao.save(productType);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test2FindById() {
		try {
			productTypeDao.findOne(this.productType.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3FindProductByItemNumber() {
		try {
			ProductType productType = productTypeDao.findProductByItemNumber(this.productType
					.getItemNumber());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3FindAll() {
		try {
			List<ProductType> productTypes = productTypeDao.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test4Delete() {
		try {
			productTypeDao.delete(productType.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
