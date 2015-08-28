package hu.neuron.ier.business.test;

import hu.neuron.ier.business.client.ClientServiceRemote;
import hu.neuron.ier.business.purchase.PurchaseServiceRemote;
import hu.neuron.ier.business.vo.ClientVO;
import hu.neuron.ier.business.vo.PurchaseVO;

import java.util.Calendar;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PurchaseServiceTest {

	private static PurchaseVO purchaseVO;
	private static ClientVO clientVO;
	private static Calendar date;
	private EJBContainer ejbContainer;

	@EJB(mappedName = "PurchaseService", name = "PurchaseService")
	PurchaseServiceRemote purchaseService;
	@EJB(mappedName = "ClientService", name = "ClientService")
	ClientServiceRemote clientService;

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

	@Test
	public void test1createPurchase() {
		try {
			clientVO = new ClientVO();
			clientVO = clientService.registrationClient(clientVO);
			purchaseVO = new PurchaseVO();
			purchaseVO.setClient(clientVO);
			purchaseVO.setDate(date);
			purchaseVO.setFullCost(123l);
			purchaseVO.setStatus("status");

			purchaseVO = purchaseService.createPurchase(purchaseVO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test2getPurchaseByClient() {
		try {
			purchaseService.getPurchaseByClient(purchaseVO.getClient());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3getAllPurchase() {
		try {
			purchaseService.getAllPurchase();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test4getPurchaseByDate() {
		try {
			purchaseService.getPurchaseByDate(purchaseVO.getDate());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test5getPurchaseByStatus() {
		try {
			purchaseService.getPurchaseByStatus(purchaseVO.getStatus());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test6deletePurchase() {
		try {
			purchaseService.deletePurchase(purchaseVO.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test99closeEJBContainer() {
		try {
			ejbContainer.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
