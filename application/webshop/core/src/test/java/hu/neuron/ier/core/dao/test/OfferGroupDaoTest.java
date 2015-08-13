package hu.neuron.ier.core.dao.test;

import hu.neuron.ier.core.dao.OfferGroupDao;
import hu.neuron.ier.core.entity.OfferGroup;

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
public class OfferGroupDaoTest {

	private static final Logger logger = Logger.getLogger(OfferGroupDaoTest.class);

	private static OfferGroup offerGroup;
	private static OfferGroup parentOfferGroup;

	@Autowired
	OfferGroupDao offerGroupDao;

	@Test
	public void test1Save() {
		try {
			offerGroup = new OfferGroup();
			offerGroup.setName("test12");
			offerGroup.setDescription("description");
			offerGroup.setParentOfferGroup(parentOfferGroup);
			offerGroup = offerGroupDao.save(offerGroup);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}

	}

	@Test
	public void test2FindOfferGroupByName() {
		try {
			OfferGroup offerGroup = offerGroupDao.findOfferGroupByName(this.offerGroup.getName());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3FindAll() {
		try {
			List<OfferGroup> offerGroupss = offerGroupDao.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}

	}

	@Test
	public void test4Delete() {
		try {
			offerGroupDao.delete(offerGroup.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
}
