package hu.neuron.ier.core.dao.test;

import hu.neuron.ier.core.dao.ClientDao;
import hu.neuron.ier.core.entity.Client;
import hu.neuron.ier.core.entity.User;

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
public class ClientDaoTest {

	private static final Logger logger = Logger.getLogger(ClientDaoTest.class);

	private static Client client;

	@Autowired
	ClientDao clientDao;

	@Test
	public void testSave() {
		try {
			client = new Client();
			client.setClientId(6L);
			client.setClientName("test");
			client.setPassword("test");
			client.setFullName("Test Elek");
			client = clientDao.save(client);
			logger.info("user: " + client);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testUpdate() {
		try {
			client.setClientName("C");
			clientDao.save(client);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testFind() {
		try {
			Client rvDTO = clientDao.findOne(client.getId());
			logger.info("rv: " + rvDTO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testFindAll() {
		try {
			List<Client> clients = clientDao.findAll();
			for (Client clientDTO : clients) {
				logger.info("rv: " + clientDTO);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testDelete() {
		try {
			clientDao.delete(client.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
}
