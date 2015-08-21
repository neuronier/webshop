package hu.neuron.ier.business.test;

import hu.neuron.ier.business.user.UserServiceRemote;
import hu.neuron.ier.business.vo.UserVO;

import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserServiceImplTest {

	private UserVO userVO;
	private EJBContainer ejbContainer;


	@EJB(name = "UserService", mappedName = "UserService")
	// UserServiceImpl userServiceImpl;
	UserServiceRemote userServiceImpl;

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
	public void test1() throws Exception {
		try {
			userVO = new UserVO();
			userVO.setEmail("asd");
			userVO.setFullName("asd");
			userVO.setPassword("asdf");
			userVO.setPhone("123");
			userVO.setUserName("test");
			userServiceImpl.saveUser(userVO);
			userVO = userServiceImpl.registrationUser(userVO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void test99closeEJBContainer() {
		ejbContainer.close();
	}

}
