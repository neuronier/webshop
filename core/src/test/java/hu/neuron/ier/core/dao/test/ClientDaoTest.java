package hu.neuron.ier.core.dao.test;

import hu.neuron.ier.core.dao.ClientDao;
import hu.neuron.ier.core.entity.Client;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public void test1Save() {
		try {
			client = new Client();
			client.setClientId("ti");
			client.setUserName("test");
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
	public void test2Update() {
		try {
			client.setUserName("C");
			clientDao.save(client);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3Find() {
		try {
			Client rvDTO = clientDao.findOne(client.getId());
			logger.info("rv: " + rvDTO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void test4FindAll() {
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
	public void test5FindByUserName() {
		try {
			Client newClient = new Client();
			newClient.setClientId("ől");
			newClient.setBillingAddress(client.getBillingAddress());
			newClient.setDeliveryAddress(client.getBillingAddress());
			newClient.setEmail("sajtszag@illat.hu");
			newClient.setUserName("kutyafüle");
			newClient.setFullName("Kala Pál");
			newClient = clientDao.save(newClient);
			Client rv = clientDao.findByUserName(client.getUserName());
			Assert.assertEquals(client.getId().longValue(), rv.getId().longValue());
			Assert.assertEquals(client.getFullName(), rv.getFullName());
			
			
			logger.info("rv: " + rv);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void test6FindByUserNameStartsWithTest() {
		try {
			List<Client> clients = new ArrayList<Client>();
			Client client;
			for(int i= 1; i<=10; i++){
				client = new Client();
				client.setUserName("client" + i);
				client.setPassword("client" + (2 * i));
				clients.add(client);
			}
			clients = clientDao.save(clients);
			
			for(Client c : clients){
				logger.info("client: " + c.getUserName());
			}
			Sort sort = new Sort(Sort.Direction.DESC, "userName");
			Pageable page = new PageRequest(0, 2,sort);;
			Page<Client> clientpage;
			
			for(int lap = 0; lap< 5; lap++){
				
				clientpage = clientDao.findByUserNameStartsWith("cli", page);
				logger.info("getTotalElements: " + clientpage.getTotalElements());
				logger.info("getTotalPages: " + clientpage.getTotalPages());
				logger.info("getSize: " + clientpage.getSize());
				logger.info("getNumber: " + clientpage.getNumber());
				logger.info("getNumberOfElements: "
						+ clientpage.getNumberOfElements());
				
				for(Client c: clientpage){
					logger.info("Page " + lap + " client: " + c.getUserName());
				}
				
				for (Client c : clientpage.getContent()) {
					logger.info("Page " + lap + " content client: " + c.getUserName());
				}
				page = clientpage.nextPageable();
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
		
	@Test
	public void test7Delete() {
		try {
			Client c = clientDao.findByUserName("kutyafüle");
			clientDao.delete(client.getId());
			clientDao.delete(c);
			clientDao.deleteAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
}
