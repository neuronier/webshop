package hu.neuron.ier.core.dao;

import hu.neuron.ier.core.entity.Client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface ClientDao extends JpaRepository<Client, Long> {
	
	Client findByBClientByName(@Param("clientName")String name) throws Exception;
	
	Page<Client> findByClientNameStartsWith(String filter,Pageable pageable);

}
