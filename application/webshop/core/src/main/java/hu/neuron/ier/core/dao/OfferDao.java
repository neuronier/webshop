package hu.neuron.ier.core.dao;

import hu.neuron.ier.core.entity.Offer;
import hu.neuron.ier.core.entity.OfferGroup;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface OfferDao extends JpaRepository<Offer, Long> {

	List<Offer> findOfferByName(String name) throws Exception;
	
	List<Offer> findOfferByAction(boolean action) throws Exception;
	
	List<Offer> findOfferByFeatured(boolean featured) throws Exception;
	List<Offer> findOfferByParentOfferGroup(OfferGroup parent) throws Exception;
	
	@Query("SELECT u FROM Offer u WHERE u.name LIKE CONCAT('%', :key, '%')") 
	List<Offer> searchOffer(@Param("key")String key) throws Exception;
	
}
