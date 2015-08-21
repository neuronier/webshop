package hu.neuron.ier.core.dao.test;

import hu.neuron.ier.core.dao.OrderElementDao;
import hu.neuron.ier.core.dao.OrdersDao;
import hu.neuron.ier.core.entity.OrderElement;
import hu.neuron.ier.core.entity.Orders;

import java.util.ArrayList;
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
public class OrdersDaoTest {

	private static final Logger logger = Logger.getLogger(OrdersDaoTest.class);

	private static Orders orders;

	@Autowired
	OrdersDao ordersDao;
	@Autowired
	OrderElementDao orderElementDao;

	@Test
	public void test1save() {
		try {
			List<OrderElement> orderElements = new ArrayList<OrderElement>();
			orderElements.add(orderElementDao.save(new OrderElement()));
			orders = new Orders();
			orders.setStatus("status");
			orders.setDate(null);
			orders.setOrdersId(1l);
			orders.setOrderElements(orderElements);
			orders = ordersDao.save(orders);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testfindOrdersByOrdersId() {
		try {
			Orders orders = ordersDao.findOrdersByOrdersId(this.orders.getOrdersId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testfindOrdersByStatus() {
		try {
			List<Orders> orders = ordersDao.findOrdersByStatus(this.orders.getStatus());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test4findAll() {
		try {
			List<Orders> orders = ordersDao.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test5delete() {
		try {
			ordersDao.delete(orders.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
}
