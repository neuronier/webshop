package hu.neuron.ier.core.dao;

import hu.neuron.ier.core.entity.OfferGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface OfferGroupDao  extends JpaRepository<OfferGroup, Long> {

	OfferGroup findOfferGroupByName(String name) throws Exception;
}
