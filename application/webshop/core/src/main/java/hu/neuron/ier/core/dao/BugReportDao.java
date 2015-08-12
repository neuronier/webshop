package hu.neuron.ier.core.dao;

import java.util.List;

import hu.neuron.ier.core.entity.BugReport;


import hu.neuron.ier.core.entity.Client;
import hu.neuron.ier.core.entity.ProductType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface BugReportDao extends JpaRepository<BugReport, Long>{


	List<BugReport> findByClient(Client client) throws Exception;
	
	List<BugReport> findByProductType(ProductType productType) throws Exception;
}
