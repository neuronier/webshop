package hu.neuron.ier.business.test;

import hu.neuron.ier.business.bugreport.BugReportServiceRemote;
import hu.neuron.ier.business.client.ClientServiceRemote;
import hu.neuron.ier.business.producttype.ProductTypeServiceRemote;
import hu.neuron.ier.business.vo.BugReportVO;
import hu.neuron.ier.business.vo.ClientVO;
import hu.neuron.ier.business.vo.ProductTypeVO;

import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BugReportServiceImplTest {

	private static BugReportVO bugReportVO;
	private static ClientVO clientVO;
	private static ProductTypeVO productTypeVO;
	private EJBContainer ejbContainer;

	@EJB(mappedName = "BugReportService", name = "BugReportService")
	BugReportServiceRemote bugReportService;
	@EJB(mappedName = "ClientService", name = "ClientService")
	ClientServiceRemote clientService;
	@EJB(mappedName = "ProductTypeService", name = "ProductTypeService")
	ProductTypeServiceRemote productTypeService;

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
	public void test1createBugReport() {
		try {
			clientVO = new ClientVO();
			clientVO = clientService.registrationClient(clientVO);
			productTypeVO = new ProductTypeVO();
			productTypeVO = productTypeService.createProductType(productTypeVO);

			bugReportVO = new BugReportVO();
			bugReportVO.setClientVO(clientVO);
			bugReportVO.setProblem("problem");
			bugReportVO.setProductTypeVO(productTypeVO);

			bugReportVO = bugReportService.createBugReport(bugReportVO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test2updateProblem() {
		try {
			bugReportVO = bugReportService.updateProblem(bugReportVO.getId(), "problem2");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3getBugReportByCient() {
		try {
			bugReportService.getBugReportByCient(clientVO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test4getBugReportByProductType() {
		try {
			bugReportService.getBugReportByProductType(productTypeVO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test5getAllBugReport() {
		try {
			bugReportService.getAllBugReport();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test6deleteBugReport() {
		try {
			bugReportService.deleteBugReport(bugReportVO.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test99closeEJBContainer() {
		ejbContainer.close();
	}

}
