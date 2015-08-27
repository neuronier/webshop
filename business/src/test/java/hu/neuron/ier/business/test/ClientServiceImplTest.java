package hu.neuron.ier.business.test;

import hu.neuron.ier.business.address.AddressServiceRemote;
import hu.neuron.ier.business.client.ClientServiceRemote;
import hu.neuron.ier.business.vo.AddressVO;
import hu.neuron.ier.business.vo.ClientVO;

import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientServiceImplTest {

	private static ClientVO clientVO;
	private static AddressVO billingAddress;
	private EJBContainer ejbContainer;

	@EJB(mappedName = "ClientService", name = "ClientService")
	ClientServiceRemote clientService;
	@EJB(mappedName = "AddressService", name = "AddressService")
	AddressServiceRemote addressService;

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
	public void test1RegistrationClient() {
		try {
			clientVO = new ClientVO();
			clientVO.setFullName("teszt");
			clientVO.setEmail("teszt");
			clientVO.setPassword("teszt");
			clientVO.setPhone("teszt");
			clientVO.setUserName("teszt");
			clientVO = clientService.registrationClient(clientVO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Test
	public void test2FindClientByName() {
		try {
			clientService.findClientByName(clientVO.getUserName());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3GetClientList() {
		try {
			clientService.getClientList();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test4GetRoleByName() {
		try {
			clientService.getRoleByName("teszt");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test5addAddressToClient() {
		try {
			billingAddress = new AddressVO();
			billingAddress.setCity("teszt");
			billingAddress.setHouse("teszt");
			billingAddress.setStreet("teszt");
			billingAddress = addressService.createAddress(billingAddress);
			clientService.addAddressToClient(clientVO.getId(), true, billingAddress, null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void test99closeEJBContainer() {
		ejbContainer.close();
	}

}
