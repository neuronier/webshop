package hu.neuron.ier.core.dao;

import hu.neuron.ier.core.entity.BugMessage;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface BugMessageDao extends JpaRepository<BugMessage, Long>{
	
	List<BugMessage> findByReportId(@Param("reportId") String reportId);
	
	BugMessage findByMessageId(@Param("messageId") String messageId);
	
	BugMessage findFirst1ByReportId(String reportId, Sort sort);
}