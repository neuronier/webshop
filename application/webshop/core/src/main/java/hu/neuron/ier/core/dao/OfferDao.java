package hu.neuron.ier.core.dao;

import hu.neuron.ier.core.entity.Offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface OfferDao  extends JpaRepository<Offer, Long> {

	Offer findOfferByName(String name) throws Exception;
	
	Offer findOfferByItemNumber(Long itemNumber) throws Exception;
	
	Offer findOfferByID(Long id) throws Exception;
}
