package hu.neuron.ier.core.dao.test;

import hu.neuron.ier.core.dao.AddressDao;
import hu.neuron.ier.core.entity.Address;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
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
public class AddressDaoTest {

	private static final Logger logger = Logger.getLogger(AddressDaoTest.class);

	private static Address address;

	@Autowired
	AddressDao addressDao;

	@Test
	public void test1Save() {
		logger.info("test1Save start...");
		try {
			address = new Address();
			address.setCity("test");
			address.setHouse("test");
			address.setPostcode(123l);
			address.setStreet("test");
			address = addressDao.save(address);
			logger.info("Address id: " + address.getId());
			Address address2 = new Address();
			address2.setCity(address.getCity());
			address2.setHouse(address.getHouse());
			address2.setPostcode(address.getPostcode());
			address2.setStreet(address.getStreet());
			address2 = addressDao.save(address2);
			logger.info("Address2 id:" + address2.getId());
			Assert.assertNotEquals(address.getId(), address2.getId());
			addressDao.delete(address2);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		logger.info("test1Save end");

	}

	@Test
	public void test2FindAddressByCity() {
		logger.info("test2FindAddressByCity start...");
		try {
			List<Address> adList = addressDao.findAddressByCity(address.getCity());
			// meg kell találnia, mert létezik
			Assert.assertTrue(adList != null);
			List<Address> nullList = addressDao.findAddressByCity("nameless city");
			logger.info("nullList is null: " + (nullList == null));
			if (nullList != null) {
				logger.info("nullList size: " + nullList.size());
			}
			Assert.assertEquals(0, nullList.size());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		logger.info("test2FindAddressByCity end");
	}

	@Test
	public void test3FindAll() {
		logger.info("test3FindAll start...");
		try {
			List<Address> addresses = addressDao.findAll();
			Assert.assertTrue(addresses.size() > 0);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		logger.info("test3FindAll end");
	}

	@Test
	public void test5Delete() {
		logger.info("test5Delete start...");
		try {
			addressDao.delete(address);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		logger.info("test5Delete end");
	}

}
