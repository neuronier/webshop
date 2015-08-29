package hu.neuron.ier.business.test;

import hu.neuron.ier.business.offer.OfferServiceRemote;
import hu.neuron.ier.business.offergroup.OfferGroupServiceRemote;
import hu.neuron.ier.business.vo.OfferGroupVO;
import hu.neuron.ier.business.vo.OfferVO;

import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OfferGroupServiceImplTest {

	private static OfferGroupVO offerGroup;
	private static OfferGroupVO parentOfferGroup;
	private static OfferGroupVO parentOfferGroup2;
	private static OfferVO offer;
	private static OfferGroupVO parent;
	private static OfferGroupVO child1;
	private static OfferGroupVO child2;
	private static OfferGroupVO child3;
	private static OfferGroupVO child4;
	private static OfferGroupVO root;
	private EJBContainer ejbContainer;

	@EJB(mappedName = "OfferGroupService", name = "OfferGroupService")
	OfferGroupServiceRemote offerGroupService;
	@EJB(mappedName = "OfferService", name = "OfferService")
	OfferServiceRemote offerService;

	@Before
	public void startTheContainer() throws Exception {
		final Properties p = new Properties();

		p.put("hu.neuron.ier.jpa.hibernate.hbm2ddl.auto", "create");
		p.put("hu.neuron.ier.jpa.hibernate.dialect",
				"org.hibernate.dialect.HSQLDialect");
		p.put("hu.neuron.ier.database.test", "new://Resource?type=DataSource");
		p.put("hu.neuron.ier.database.test.JtaManaged", "false");
		p.put("hu.neuron.ier.database.test.JdbcDriver", "org.hsqldb.jdbcDriver");
		p.put("hu.neuron.ier.database.test.JdbcUrl", "jdbc:hsqldb:mem:aname");

		ejbContainer = EJBContainer.createEJBContainer(p);
		ejbContainer.getContext().bind("inject", this);
	}

	@Test
	public void test1createOfferGroup() {
		try {
			offerGroup = new OfferGroupVO();
			parentOfferGroup = new OfferGroupVO();
			parentOfferGroup = offerGroupService
					.createOfferGroup(parentOfferGroup);
			offerGroup.setDescription("description");
			offerGroup.setName("name");
			offerGroup.setParentOfferGroup(parentOfferGroup);
			offerGroup = offerGroupService.createOfferGroup(offerGroup);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test2updateOfferGroupName() {
		try {
			offerGroup = offerGroupService.updateOfferGroupName(
					offerGroup.getId(), "name2");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3updateOfferGroupDescription() {
		try {
			offerGroup = offerGroupService.updateOfferGroupDescription(
					offerGroup.getId(), "description2");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test4offerGroupToOfferGroup() {
		try {
			parentOfferGroup2 = new OfferGroupVO();
			parentOfferGroup2 = offerGroupService
					.createOfferGroup(parentOfferGroup2);
			offerGroup = offerGroupService.offerGroupToOfferGroup(
					offerGroup.getId(), parentOfferGroup2.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test5offerToOfferGroup() {
		try {
			offer = new OfferVO();
			offer = offerService.createOffer(offer);
			offerGroup = offerGroupService.offerToOfferGroup(offer.getId(),
					offerGroup.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test6findAllOfferGroup() {
		try {
			offerGroupService.findAllOfferGroup();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test7updateOfferGroupActiveRecursivelyToTrue() {

		try {
			root = new OfferGroupVO();
			root = offerGroupService.createOfferGroup(root);
			//parent
			parent = new OfferGroupVO();
			parent.setName("parent");
			parent.setParentOfferGroup(root);
			parent.setActive(false);
			parent = offerGroupService.createOfferGroup(parent);
			Assert.assertEquals(root.getId(), parent.getParentOfferGroup().getId());
			Assert.assertFalse(parent.getActive());
			//child1
			child1 = new OfferGroupVO();
			child1.setName("child1");
			child1.setParentOfferGroup(parent);
			child1.setActive(false);
			child1 = offerGroupService.createOfferGroup(child1);
			Assert.assertEquals(parent.getId(), child1.getParentOfferGroup().getId());
			Assert.assertFalse(child1.getActive());
			//child2
			child2 = new OfferGroupVO();
			child2.setName("child2");
			child2.setActive(false);
			child2.setParentOfferGroup(parent);
			child2 = offerGroupService.createOfferGroup(child2);
			Assert.assertEquals(parent.getId(), child2.getParentOfferGroup().getId());
			Assert.assertFalse(child2.getActive());
			//child3
			child3 = new OfferGroupVO();
			child3.setName("child3");
			child3.setActive(false);
			child3.setParentOfferGroup(child1);
			child3 = offerGroupService.createOfferGroup(child3);
			Assert.assertEquals(child1.getId(), child3.getParentOfferGroup().getId());
			Assert.assertFalse(child3.getActive());
			//child4
			child4 = new OfferGroupVO();
			child4.setName("child4");
			child4.setActive(false);
			child4.setParentOfferGroup(child2);
			child4 = offerGroupService.createOfferGroup(child4);
			Assert.assertEquals(child2.getId(), child4.getParentOfferGroup().getId());
			Assert.assertFalse(child4.getActive());
			offerGroupService.updateOfferGroupActiveRecursively(parent, true);
			//parent childs
			List<OfferGroupVO> vos = offerGroupService.findOfferGroupByParentOfferGroup(root);
			for(OfferGroupVO ogvo : vos){
				Assert.assertTrue(ogvo.getActive());
			}
			//child1 childs
			vos = offerGroupService.findOfferGroupByParentOfferGroup(child1);
			
			for(OfferGroupVO ogvo : vos){
				Assert.assertTrue(ogvo.getActive());
			}
			
			//child2 childs
			vos = offerGroupService.findOfferGroupByParentOfferGroup(child2);
			for(OfferGroupVO ogvo : vos){
				Assert.assertTrue(ogvo.getActive());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test8updateOfferGroupActiveRecursivelyToFalse() {

		try {
			root = new OfferGroupVO();
			root = offerGroupService.createOfferGroup(root);
			//parent
			parent = new OfferGroupVO();
			parent.setName("parent");
			parent.setParentOfferGroup(root);
			parent.setActive(true);
			parent = offerGroupService.createOfferGroup(parent);
			Assert.assertEquals(root.getId(), parent.getParentOfferGroup().getId());
			Assert.assertTrue(parent.getActive());
			//child1
			child1 = new OfferGroupVO();
			child1.setName("child1");
			child1.setParentOfferGroup(parent);
			child1.setActive(true);
			child1 = offerGroupService.createOfferGroup(child1);
			Assert.assertEquals(parent.getId(), child1.getParentOfferGroup().getId());
			Assert.assertTrue(child1.getActive());
			//child2
			child2 = new OfferGroupVO();
			child2.setName("child2");
			child2.setActive(true);
			child2.setParentOfferGroup(parent);
			child2 = offerGroupService.createOfferGroup(child2);
			Assert.assertEquals(parent.getId(), child2.getParentOfferGroup().getId());
			Assert.assertTrue(child2.getActive());
			//child3
			child3 = new OfferGroupVO();
			child3.setName("child3");
			child3.setActive(true);
			child3.setParentOfferGroup(child1);
			child3 = offerGroupService.createOfferGroup(child3);
			Assert.assertEquals(child1.getId(), child3.getParentOfferGroup().getId());
			Assert.assertTrue(child3.getActive());
			//child4
			child4 = new OfferGroupVO();
			child4.setName("child4");
			child4.setActive(true);
			child4.setParentOfferGroup(child2);
			child4 = offerGroupService.createOfferGroup(child4);
			Assert.assertEquals(child2.getId(), child4.getParentOfferGroup().getId());
			Assert.assertTrue(child4.getActive());
			offerGroupService.updateOfferGroupActiveRecursively(parent, false);
			//parent childs
			List<OfferGroupVO> vos = offerGroupService.findOfferGroupByParentOfferGroup(root);
			for(OfferGroupVO ogvo : vos){
				Assert.assertFalse(ogvo.getActive());
			}
			//child1 childs
			vos = offerGroupService.findOfferGroupByParentOfferGroup(child1);
			
			for(OfferGroupVO ogvo : vos){
				Assert.assertFalse(ogvo.getActive());
			}
			
			//child2 childs
			vos = offerGroupService.findOfferGroupByParentOfferGroup(child2);
			for(OfferGroupVO ogvo : vos){
				Assert.assertFalse(ogvo.getActive());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test91deleteOfferGroup() {
		try {
			offerGroupService.deleteOfferGroup(parentOfferGroup2.getId());
			offerGroupService.deleteOfferGroup(offerGroup.getId());
			offerGroupService.deleteOfferGroup(parent.getId());
			offerGroupService.deleteOfferGroup(child1.getId());
			offerGroupService.deleteOfferGroup(child2.getId());
			offerGroupService.deleteOfferGroup(child3.getId());
			offerGroupService.deleteOfferGroup(child4.getId());
			offerGroupService.deleteOfferGroup(parentOfferGroup.getId());
			offerGroupService.deleteOfferGroup(root.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test99closeEJBContainer() {
		ejbContainer.close();
	}

}
