package hu.neuron.ier.core.dao;

import hu.neuron.ier.core.entity.BugReport;

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
public interface BugReportDao extends JpaRepository<BugReport, Long>{


	BugReport findByReportId(@Param("reportId") String reportId);
	
	List<BugReport> findByClientId(@Param("clientId") String clientId);
	
	Page<BugReport> findByReportIdStartsWith(String filter,Pageable pageable);
	
	Page<BugReport> findByStatusStartsWith(String filter,Pageable pageable);
	
	Page<BugReport> findBySubjectStartsWith(String filter,Pageable pageable);

	Page<BugReport> findByClientIdIn(List<String> clientIdList,Pageable pageable);
	
	Long countByStatus(String status);

}
