package hu.neuron.ier.core.dao.test;

import hu.neuron.ier.core.dao.ClientDao;
import hu.neuron.ier.core.dao.PurchaseDao;
import hu.neuron.ier.core.entity.Client;
import hu.neuron.ier.core.entity.Purchase;

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
public class PurchaseDaoTest {
	private static final Logger logger = Logger.getLogger(PurchaseDaoTest.class);

	private static Purchase purchase;
	private static Client client;

	@Autowired
	PurchaseDao purchaseDao;
	@Autowired
	ClientDao clientDao;

	@Test
	public void test1Save() {
		try {
			purchase = new Purchase();
			purchase.setClient(client);
			purchase.setFullCost(1l);
			purchase.setStatus("ads");
			purchaseDao.save(purchase);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}

	}

	@Test
	public void test2FindById() {
		try {
			purchaseDao.findOne(purchase.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3FindByClient() {
		try {
			purchaseDao.findByClient(client);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test4FindAll() {
		try {
			purchaseDao.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test4Delete() {
		try {
			purchaseDao.delete(purchase);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
