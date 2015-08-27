package hu.neuron.ier.business.bugreport;

import hu.neuron.ier.business.vo.BugMessageVO;

import java.util.List;

public interface BugMessageServiceRemote {
	
	public BugMessageVO saveBugMessage(BugMessageVO bugMessage);
	
	public void removeBugMessage(BugMessageVO bugMessage);
	
	public List<BugMessageVO> findByReportId(String reportId);
	
	public BugMessageVO findByBugMessageId(String bugMessageId);

}