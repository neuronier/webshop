package hu.neuron.ier.core.dao;

import hu.neuron.ier.core.entity.Client;
import hu.neuron.ier.core.entity.Purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface OrderDao extends JpaRepository<Purchase, Long> {
	
	List<Purchase> findByClient(@Param("client") Client client) throws Exception;

}
