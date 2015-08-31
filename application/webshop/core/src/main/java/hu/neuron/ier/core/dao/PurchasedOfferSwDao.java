package hu.neuron.ier.core.dao;

import hu.neuron.ier.core.entity.Purchase;
import hu.neuron.ier.core.entity.PurchasedOfferSw;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface PurchasedOfferSwDao extends JpaRepository<PurchasedOfferSw, Long> {
	
}
