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
public class OfferServiceImplTest {
	private static OfferVO offerVO;
	private static OfferGroupVO parentOfferGroup;
	private EJBContainer ejbContainer;


	@EJB(mappedName = "OfferService", name = "OfferService")
	OfferServiceRemote offerService;

	@EJB(mappedName = "OfferGroupService", name = "OfferGroupService")
	OfferGroupServiceRemote offerGroupService;

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
	public void test1CreateOffer() {
		try {
			offerVO = new OfferVO();
			offerVO.setAction(true);
			offerVO.setDescription("teszt");
			offerVO.setFeatured(true);
			offerVO.setName("asd");
			offerVO.setNewCost(12l);
			offerVO.setOriginalCost(1l);
			parentOfferGroup = new OfferGroupVO();
			parentOfferGroup = offerGroupService.createOfferGroup(parentOfferGroup);
			offerVO.setParentOfferGroup(parentOfferGroup);
			offerVO = offerService.createOffer(offerVO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test2GetAllOffer() {
		try {
			offerService.getAllOffers();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3GetActionOffers() {
		try {
			offerService.getActionOffers(offerVO.isAction());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test4GetFeaturedOffers() {
		try {
			offerService.getFeaturedOffers(offerVO.isFeatured());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test5GetOffersByName() {
		try {
			offerService.getOffersByName(offerVO.getName());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test6UpdateOfferCost() {
		try {
			offerVO = offerService.updateOfferCost(offerVO.getId(), 1234l);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test7UpdateOfferDescription() {
		try {
			offerVO = offerService.updateOfferDescription(offerVO.getId(), "description");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test8UpdateOfferName() {
		try {
			offerVO = offerService.updateOfferName(offerVO.getId(), "name");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test9DeleteOffer() {
		try {
			offerService.deleteOffer(offerVO.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
