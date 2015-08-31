package hu.neuron.ier.core.dao;

import hu.neuron.ier.core.entity.Offer;
import hu.neuron.ier.core.entity.OfferGroup;
import hu.neuron.ier.core.entity.User;

import java.util.List;

import javax.persistence.JoinColumn;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	Page<Offer> findByNameStartsWith(String filter,Pageable pageable);
	
//	@Query("SELECT u FROM Offer u WHERE u IN (SELECT o.offer FROM PurchasedOfferSw o WHERE z.purchase= :key)")
	@Query("SELECT u FROM Offer u WHERE u IN (SELECT z.offer FROM PurchasedOfferSw z WHERE z.purchase.id=:key)")
	List<Offer> getOffersFromPurchase(@Param("key")Long key);

	
	
}
