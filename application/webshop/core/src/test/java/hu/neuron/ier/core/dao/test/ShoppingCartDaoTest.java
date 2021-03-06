package hu.neuron.ier.core.dao.test;

import hu.neuron.ier.core.dao.ShoppingCartDao;
import hu.neuron.ier.core.entity.ShoppingCart;

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
public class ShoppingCartDaoTest {

	private static final Logger logger = Logger.getLogger(ShoppingCartDaoTest.class);

	private static ShoppingCart shoppingCart;

	@Autowired
	ShoppingCartDao shoppingCartDao;

	@Test
	public void test1Save() {
		try {
			shoppingCart = new ShoppingCart();
			shoppingCartDao.save(shoppingCart);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test2FindById() {
		try {
			shoppingCartDao.findOne(shoppingCart.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3FindAll() {
		try {
			shoppingCartDao.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test4Delete() {
		try {
			shoppingCartDao.delete(shoppingCart.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
