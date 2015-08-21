package hu.neuron.ier.business.test;

import hu.neuron.ier.business.offer.OfferServiceRemote;
import hu.neuron.ier.business.shoppingcart.ShoppingCartRemote;
import hu.neuron.ier.business.vo.OfferVO;
import hu.neuron.ier.business.vo.ShoppingCartVO;

import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShoppingCartServiceImplTest {

	private static ShoppingCartVO shoppingCartVO;
	private static OfferVO offerVO;
	private EJBContainer ejbContainer;


	@EJB(mappedName = "ShoppingCartService", name = "ShoppingCartService")
	ShoppingCartRemote shoppingCartService;
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
	public void test1AddOffer() {
		try {
			shoppingCartVO = new ShoppingCartVO();
			shoppingCartVO = shoppingCartService.createShoppingCart(shoppingCartVO);
			offerVO = new OfferVO();
			offerVO = offerService.createOffer(offerVO);
			shoppingCartVO = shoppingCartService.addOffer(shoppingCartVO.getId(), offerVO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
