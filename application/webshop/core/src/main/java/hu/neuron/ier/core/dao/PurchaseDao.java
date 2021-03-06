package hu.neuron.ier.core.dao;

import hu.neuron.ier.core.entity.Client;
import hu.neuron.ier.core.entity.Purchase;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface PurchaseDao extends JpaRepository<Purchase, Long> {

	List<Purchase> findByClient(@Param("client") Client client) throws Exception;

	List<Purchase> findByDate(Calendar date) throws Exception;

	List<Purchase> findByStatus(String status) throws Exception;
	
	@Query("select COUNT(p) from Purchase p where YEAR(p.date) = ?1 AND MONTH(p.date) = ?2")
	int findCountByMonth(int year, int month);
	
	@Query("select p from Purchase p where YEAR(p.date) = ?1 AND MONTH(p.date) = ?2")
	List<Purchase> findByMonth(int year, int month);

}
