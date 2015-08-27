package hu.neuron.ier.core.dao.test;

import hu.neuron.ier.core.dao.ClientDao;
import hu.neuron.ier.core.dao.PurchaseDao;
import hu.neuron.ier.core.entity.Client;
import hu.neuron.ier.core.entity.Purchase;

import java.util.Calendar;

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
	private static Calendar date;

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
			date = new Calendar() {

				@Override
				public void roll(int field, boolean up) {
					// TODO Auto-generated method stub

				}

				@Override
				public int getMinimum(int field) {
					// TODO Auto-generated method stub
					return 0;
				}

				@Override
				public int getMaximum(int field) {
					// TODO Auto-generated method stub
					return 0;
				}

				@Override
				public int getLeastMaximum(int field) {
					// TODO Auto-generated method stub
					return 0;
				}

				@Override
				public int getGreatestMinimum(int field) {
					// TODO Auto-generated method stub
					return 0;
				}

				@Override
				protected void computeTime() {
					// TODO Auto-generated method stub

				}

				@Override
				protected void computeFields() {
					// TODO Auto-generated method stub

				}

				@Override
				public void add(int field, int amount) {
					// TODO Auto-generated method stub

				}
			};
			purchase = purchaseDao.save(purchase);
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
			purchaseDao.findByClient(purchase.getClient());
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
	public void test4findByDate() {
		try {
			purchaseDao.findByDate(purchase.getDate());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test5findByStatus() {
		try {
			purchaseDao.findByStatus(purchase.getStatus());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test6Delete() {
		try {
			purchaseDao.delete(purchase);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
