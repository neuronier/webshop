package hu.neuron.ier.core.dao.test;

import hu.neuron.ier.core.dao.OfferGroupDao;
import hu.neuron.ier.core.entity.OfferGroup;

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
public class OfferGroupDaoTest {

	private static final Logger logger = Logger
			.getLogger(OfferGroupDaoTest.class);

	private static OfferGroup offerGroup1;
	private static OfferGroup offerGroup2;

	private static OfferGroup parentOfferGroup;

	@Autowired
	OfferGroupDao offerGroupDao;

	@Test
	public void test1Save() {
		try {
			parentOfferGroup = new OfferGroup();
			parentOfferGroup.setName("parent");
			parentOfferGroup.setDescription("parentDescription");
			parentOfferGroup.setActive(true);
			parentOfferGroup = offerGroupDao.save(parentOfferGroup);
			logger.info("parent mentve!");
			offerGroup1 = new OfferGroup();
			offerGroup1.setName("test12");
			offerGroup1.setDescription("description");
			offerGroup1.setActive(true);
			offerGroup1.setParentOfferGroup(parentOfferGroup);
			offerGroup1 = offerGroupDao.save(offerGroup1);
			logger.info("offerGroup1 mentve!");
			offerGroup2 = new OfferGroup();
			offerGroup2.setName("test12");
			offerGroup2.setDescription("description2");
			offerGroup2.setActive(true);
			offerGroup2.setParentOfferGroup(parentOfferGroup);
			offerGroup2 = offerGroupDao.save(offerGroup2);
			logger.info("offerGroup2 mentve!");
			OfferGroup withOtherName = new OfferGroup();
			withOtherName.setName("other");
			withOtherName.setDescription("otherdescription");
			withOtherName.setActive(false);
			withOtherName.setParentOfferGroup(parentOfferGroup);
			withOtherName = offerGroupDao.save(withOtherName);
			logger.info("witOtherName mentve!");
			OfferGroup parentless = new OfferGroup();
			parentless.setName("parntless");
			parentless.setDescription("anything");
			parentless.setActive(true);
			parentless = offerGroupDao.save(parentless);
			logger.info("parentless mentve!");
			logger.info("save test sikeresen lefutott!");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}

	}

	@Test
	public void test2FindOfferGroupByName() {
		try {
			List<OfferGroup> offerGroups = offerGroupDao.findOfferGroupByName(offerGroup1
					.getName());
			for(OfferGroup o : offerGroups){
				Assert.assertEquals("test12", o.getName());
				logger.info("Name: " + o.getName());
				logger.info("Description: " + o.getDescription());
				Assert.assertEquals("parent", o.getParentOfferGroup().getName());
				logger.info("ParentName: " + o.getParentOfferGroup().getName());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3FindAll() {
		try {
			List<OfferGroup> offerGroups = offerGroupDao.findAll();
			for(OfferGroup o : offerGroups){
				logger.info("Name: " + o.getName());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}

	}
	
	@Test
	public void test41FindOfferGroupByParentOfferGroup() {
		try {
			List<OfferGroup> offerGroups = offerGroupDao.findOfferGroupByParentOfferGroup(parentOfferGroup);
			for(OfferGroup o: offerGroups){
				Assert.assertFalse(o.getParentOfferGroup() == null);
				logger.info("Name: " + o.getName());
				logger.info("Description: " + o.getDescription());
				logger.info("ParentName: " + o.getParentOfferGroup().getName());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void test42FindOfferGroupByParentOfferGroupAndActiveTrue() {
		try {
			List<OfferGroup> offerGroups = offerGroupDao.findOfferGroupByParentOfferGroupAndActive(parentOfferGroup, true);
			Assert.assertTrue(offerGroups.size() == 2);
			for(OfferGroup o: offerGroups){
				Assert.assertFalse(o.getParentOfferGroup() == null);
				Assert.assertTrue(o.getActive());
				logger.info("Name: " + o.getName());
				logger.info("Description: " + o.getDescription());
				logger.info("ParentName: " + o.getParentOfferGroup().getName());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void test43FindOfferGroupByParentOfferGroupAndActiveFalse() {
		try {
			List<OfferGroup> offerGroups = offerGroupDao.findOfferGroupByParentOfferGroupAndActive(parentOfferGroup, false);
			Assert.assertTrue(offerGroups.size() == 1);
			for(OfferGroup o: offerGroups){
				Assert.assertFalse(o.getParentOfferGroup() == null);
				Assert.assertFalse(o.getActive());
				logger.info("Name: " + o.getName());
				logger.info("Description: " + o.getDescription());
				logger.info("ParentName: " + o.getParentOfferGroup().getName());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void test5CountOfferGroupByParentOfferGroupAndActive(){
		try {
			Integer count = new Integer(0);
			count = offerGroupDao.countOfferGroupByParentOfferGroupAndActive(parentOfferGroup, true);
			logger.info("Count1: " + count.toString());
			Assert.assertTrue("2 db aktív gyermeknek kell lennie", 2 == count.intValue());
			
			count = offerGroupDao.countOfferGroupByParentOfferGroupAndActive(parentOfferGroup, false);
			logger.info("Count2: " + count.toString());
			Assert.assertTrue("1 db inaktív gyermeknek kell lennie", 1 == count);
			
			count = offerGroupDao.countOfferGroupByParentOfferGroupAndActive(null, true);
			logger.info("Count3: " + count.toString());
			Assert.assertTrue("2 db aktív gyermeknek kell lennie, helyette " + count + " van", 2 == count);
			
			count = offerGroupDao.countOfferGroupByParentOfferGroupAndActive(null, false);
			logger.info("Count4: " + count.toString());
			Assert.assertTrue("0 db inaktív gyermeknek kell lennie, helyette " + count + " van", 0 == count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test
	public void test6Delete() {
		try {
			offerGroupDao.delete(offerGroupDao.findOfferGroupByParentOfferGroup(parentOfferGroup));
			offerGroupDao.delete(offerGroupDao.findOfferGroupByName("parntless"));
			offerGroupDao.delete(offerGroupDao.findOfferGroupByName("parent"));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
}
