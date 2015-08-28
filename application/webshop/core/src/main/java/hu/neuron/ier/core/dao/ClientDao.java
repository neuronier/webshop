package hu.neuron.ier.core.dao;

import hu.neuron.ier.core.entity.Client;

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
public interface ClientDao extends JpaRepository<Client, Long> {

	Client findByUserName(@Param("userName") String name);

	Page<Client> findByUserNameStartsWith(String filter, Pageable pageable);
	
	List<Client> findByuserNameStartsWith(String filter);
	
	Client findByClientId(String clientId);

	Client findUserByEmail(@Param("email") String email) throws Exception;
	
	
}
