package hu.neuron.ier.core.dao;

import hu.neuron.ier.core.entity.BugReport;


import hu.neuron.ier.core.entity.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface BugReportDao extends JpaRepository<BugReport, Long>{

	BugReport findbyClient(Client client) throws Exception;
	
	BugReport findbyProblem(String problem) throws Exception;
}
