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
public interface OfferGroupDao extends JpaRepository<OfferGroup, Long> {

	List<OfferGroup> findOfferGroupByName(String name) throws Exception;
	List<OfferGroup> findOfferGroupByParentOfferGroup(OfferGroup parentOfferGroup) throws Exception;
	
	@Query("SELECT u FROM OfferGroup u WHERE u.name LIKE CONCAT('%', :key, '%')")
	List<OfferGroup> searchOfferGroup(@Param("key")String key) throws Exception;
	
	
}
