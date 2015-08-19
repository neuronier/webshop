package hu.neuron.ier.core.dao;

import java.util.List;

import hu.neuron.ier.core.entity.Orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface OrdersDao extends JpaRepository<Orders, Long>{

	Orders findOrdersByOrdersId(Long id) throws Exception;
	
	List<Orders> findOrdersByStatus(String status) throws Exception;
}
