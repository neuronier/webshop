package hu.neuron.ier.core.dao.test;

import hu.neuron.ier.core.dao.OfferDao;
import hu.neuron.ier.core.entity.Offer;
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
public class OfferDaoTest {

	private static final Logger logger = Logger.getLogger(OfferDaoTest.class);

	private static Offer offer;
	private static Offer parentOffer;
	private static OfferGroup offerGroup;

	@Autowired
	OfferDao offerDao;

	@Test
	public void test1Save() {
		try {
			offer = new Offer();
			offer.setCost(12l);
			offer.setDescription("test");
			offer.setName("test123");
			offer.setParentOfferGroup(offerGroup);
			offer.setParentOffer(parentOffer);
			offer = offerDao.save(offer);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test2FindOfferByName() {
		try {
			Offer offer = offerDao.findOfferByName(this.offer.getName());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3FindAll() {
		try {
			List<Offer> bugReports = offerDao.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test4Delete() {
		try {
			offerDao.delete(offer.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
