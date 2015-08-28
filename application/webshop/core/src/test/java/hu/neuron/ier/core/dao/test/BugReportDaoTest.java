package hu.neuron.ier.core.dao.test;

import hu.neuron.ier.core.dao.BugReportDao;
import hu.neuron.ier.core.dao.ClientDao;
import hu.neuron.ier.core.dao.ProductTypeDao;
import hu.neuron.ier.core.entity.BugReport;
import hu.neuron.ier.core.entity.Client;
import hu.neuron.ier.core.entity.ProductType;

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
public class BugReportDaoTest {

	private static final Logger logger = Logger.getLogger(BugReportDaoTest.class);

	private static BugReport bugReport;
	private static Client client;
	private static ProductType productType;

	@Autowired
	BugReportDao bugReportDao;
	@Autowired
	ClientDao clientDao;
	@Autowired
	ProductTypeDao productTypeDao;

	@Test
	public void test1Save() {
		try {
			client = new Client();
			client.setUserName("test");
			client.setClientId("sda");
			client.setEmail("test");
			client.setFullName("test");
			client.setPassword("test");
			client.setPhone("123");
			client = clientDao.save(client);

			productType = new ProductType();
			productType.setItemNumber("test123");
			productType = productTypeDao.save(productType);

			bugReport = new BugReport();
			
			bugReport = bugReportDao.save(bugReport);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

//	@Test
//	public void test2FindByClient() {
//		try {
//			List<BugReport> bugReports = bugReportDao.findByClient("s");
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			throw new RuntimeException(e);
//		}
//	}
//
//	@Test
//	public void test3FindByProductType() {
//		try {
//			List<BugReport> bugReports = bugReportDao.findByProductType(productType);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			throw new RuntimeException(e);
//		}
//	}

	@Test
	public void test4FindAll() {
		try {
			List<BugReport> bugReports = bugReportDao.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test5Delete() {
		try {
			bugReportDao.delete(bugReport.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
