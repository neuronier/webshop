package hu.neuron.ier.web;


import hu.neuron.ier.business.bugreport.BugMessageServiceRemote;
import hu.neuron.ier.business.bugreport.BugReportServiceRemote;
import hu.neuron.ier.business.bugreport.BugReportServiceRemote.Status;
import hu.neuron.ier.business.vo.BugMessageVO;
import hu.neuron.ier.business.vo.BugReportVO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * Controller for the admin site.
 */
@ViewScoped
@ManagedBean(name = "bugReportManagementController")
public class BugReportManagementController implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@EJB(name = "BugReportService", mappedName = "BugReportService")
	private BugReportServiceRemote bugReportService;
	
	@EJB(name = "BugMessageService", mappedName = "BugMessageService")
	private BugMessageServiceRemote bugMessageService;
	
	private String comment;
	
	private LazyBugReportManagementModel lazyBugReportManagementModel;
	
	private BugReportVO selectedBugReport;
	
	private List<BugMessageVO> bugMessageList;
	
	public void setLazyBugReportManagementModel(
			LazyBugReportManagementModel lazyBugReportManagementModel) {
		this.lazyBugReportManagementModel = lazyBugReportManagementModel;
	}

	@PostConstruct
	public void init() {

		setLazyBugReportManagementModel(new LazyBugReportManagementModel(getBugReportService()));
	}
	
	public LazyBugReportManagementModel getLazyBugReportManagementModel() {
		return lazyBugReportManagementModel;
	}



	public void initBugReportViewerDialog(){
		if(selectedBugReport.getStatus().equals(Status.NEW.toString())){
			selectedBugReport.setStatus(Status.ONGOING.toString());
			selectedBugReport.setLastUpdate(new Date());
			bugReportService.saveBugReport(selectedBugReport);
		}
		bugMessageList = bugMessageService.findByReportId(selectedBugReport.getReportId()); 
	}

	public void sendMessage(){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		BugMessageVO bugMessage = new BugMessageVO();
		bugMessage.setText(comment);
		bugMessage.setReportId(selectedBugReport.getReportId());
		bugMessage.setDate(new Date());
		bugMessage.setOwner(user.getUsername());
		bugMessageService.saveBugMessage(bugMessage);
		
		BugReportVO report = bugReportService.findByReportId(selectedBugReport.getReportId());
		report.setLastUpdate(new Date());
		bugReportService.saveBugReport(report);
		
		bugMessageList = bugMessageService.findByReportId(selectedBugReport.getReportId());
	}
	
	public long getOngoingBugsCount(){
		return bugReportService.countOngoingBugReport();
	}

	public BugReportServiceRemote getBugReportService() {
		return bugReportService;
	}

	public void setBugReportService(BugReportServiceRemote bugReportService) {
		this.bugReportService = bugReportService;
	}

	public BugMessageServiceRemote getBugMessageService() {
		return bugMessageService;
	}

	public void setBugMessageService(BugMessageServiceRemote bugMessageService) {
		this.bugMessageService = bugMessageService;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public BugReportVO getSelectedBugReport() {
		return selectedBugReport;
	}

	public void setSelectedBugReport(BugReportVO selectedBugReport) {
		this.selectedBugReport = selectedBugReport;
	}

	public List<BugMessageVO> getBugMessageList() {
		return bugMessageList;
	}

	public void setBugMessageList(List<BugMessageVO> bugMessageList) {
		this.bugMessageList = bugMessageList;
	}
	


}