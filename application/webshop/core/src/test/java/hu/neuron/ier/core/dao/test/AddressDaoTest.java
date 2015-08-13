package hu.neuron.ier.core.dao.test;

import hu.neuron.ier.core.dao.AddressDao;
import hu.neuron.ier.core.entity.Address;

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
public class AddressDaoTest {

	private static final Logger logger = Logger.getLogger(UserDaoTest.class);

	private static Address address;

	@Autowired
	AddressDao addressDao;

	@Test
	public void test1CreateTestData() {
		try {
			address = new Address();
			address.setCity("test");
			address.setHouse("test");
			address.setPostcode(123l);
			address.setStreet("test");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test2Find() {
		try {
			Address ad = addressDao.findAddressByCity(address.getCity());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}

	}

	@Test
	public void test3FindAll() {
		try {
			List<Address> addresses = addressDao.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
