package hu.neuron.ier.core.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.neuron.ier.core.entity.Client;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface ClientDao extends JpaRepository<Client, Long> {
	
	Client findbyClientByClientName(String clientName) throws Exception;
	
	Page<Client> findByClientNameStartsWith(String filter,Pageable pageable);

}
