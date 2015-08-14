package hu.neuron.ier.core.dao;

import hu.neuron.ier.core.entity.Order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface OrderDao extends JpaRepository<Order, Long> {
	
	Order findOrderByOrderId(@Param("id") Long id) throws Exception;
	
	List<Order> findOrderByClientId(@Param("id") Long id) throws Exception;

}
