package hu.neuron.ier.business.test;

import hu.neuron.ier.business.offer.OfferServiceRemote;
import hu.neuron.ier.business.offergroup.OfferGroupServiceRemote;
import hu.neuron.ier.business.vo.OfferGroupVO;
import hu.neuron.ier.business.vo.OfferVO;

import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OfferGroupServiceImplTest {

	private static OfferGroupVO offerGroup;
	private static OfferGroupVO parentOfferGroup;
	private static OfferGroupVO parentOfferGroup2;
	private static OfferVO offer;
	private EJBContainer ejbContainer;


	@EJB(mappedName = "OfferGroupService", name = "OfferGroupService")
	OfferGroupServiceRemote offerGroupService;
	@EJB(mappedName = "OfferService", name = "OfferService")
	OfferServiceRemote offerService;

	@Before
	public void startTheContainer() throws Exception {
		final Properties p = new Properties();

		p.put("hu.neuron.ier.jpa.hibernate.hbm2ddl.auto", "create");
		p.put("hu.neuron.ier.jpa.hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		p.put("hu.neuron.ier.database.test", "new://Resource?type=DataSource");
		p.put("hu.neuron.ier.database.test.JtaManaged", "false");
		p.put("hu.neuron.ier.database.test.JdbcDriver", "org.hsqldb.jdbcDriver");
		p.put("hu.neuron.ier.database.test.JdbcUrl", "jdbc:hsqldb:mem:aname");

		ejbContainer = EJBContainer.createEJBContainer(p);
		ejbContainer.getContext().bind("inject", this);
	}
	
	@After
	public void closeContainer() throws Exception{
		ejbContainer.close();
	}

	@Test
	public void test1createOfferGroup() {
		try {
			offerGroup = new OfferGroupVO();
			parentOfferGroup = new OfferGroupVO();
			parentOfferGroup = offerGroupService.createOfferGroup(parentOfferGroup);
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
			offerGroup = offerGroupService.updateOfferGroupName(offerGroup.getId(), "name2");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3updateOfferGroupDescription() {
		try {
			offerGroup = offerGroupService.updateOfferGroupDescription(offerGroup.getId(),
					"description2");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test4offerGroupToOfferGroup() {
		try {
			parentOfferGroup2 = new OfferGroupVO();
			parentOfferGroup2 = offerGroupService.createOfferGroup(parentOfferGroup2);
			offerGroup = offerGroupService.offerGroupToOfferGroup(offerGroup.getId(),
					parentOfferGroup2.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test5offerToOfferGroup() {
		try {
			offer = new OfferVO();
			offer = offerService.createOffer(offer);
			offerGroup = offerGroupService.offerToOfferGroup(offer.getId(), offerGroup.getId());
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
	public void test7deleteOfferGroup() {
		try {
			offerGroupService.deleteOfferGroup(parentOfferGroup2.getId());
			offerGroupService.deleteOfferGroup(offerGroup.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
