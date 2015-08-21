package hu.neuron.ier.business.test;

import hu.neuron.ier.business.orderelement.OrderElementServiceRemote;
import hu.neuron.ier.business.orders.OrdersServiceRemote;
import hu.neuron.ier.business.vo.OrderElementVO;
import hu.neuron.ier.business.vo.OrdersVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrdersServiceImplTest {

	private static OrdersVO ordersVO;
	private static OrderElementVO orderElementVO;
	private EJBContainer ejbContainer;


	@EJB(mappedName = "OrdersService", name = "OrdersService")
	OrdersServiceRemote ordersService;
	@EJB(mappedName = "OrderElementService", name = "OrderElementService")
	OrderElementServiceRemote orderElementService;

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
	public void test1createOrder() {
		try {
			orderElementVO = new OrderElementVO();
			orderElementVO = orderElementService.createOrderElement(orderElementVO);
			List<OrderElementVO> orderElements = new ArrayList<OrderElementVO>();
			orderElements.add(orderElementVO);

			ordersVO = new OrdersVO();
			ordersVO.setOrdersId(1l);
			ordersVO.setStatus("status");
			ordersVO.setOrderElements(orderElements);

			ordersVO = ordersService.createOrder(ordersVO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test2updateOrderStatus() {
		try {
			ordersVO = ordersService.updateOrderStatus(ordersVO.getId(), "status2");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3getAllOrders() {
		try {
			List<OrdersVO> ordersVOs = ordersService.getAllOrders();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test4getOrdersByStatus() {
		try {
			List<OrdersVO> ordersVOs = ordersService.getOrdersByStatus(ordersVO.getStatus());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test5deleteOrders() {
		try {
			ordersService.deleteOrders(ordersVO.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void test99closeEJBContainer() {
		ejbContainer.close();
	}

}
