package hu.neuron.ier.web;


import hu.neuron.ier.business.bugreport.BugMessageServiceRemote;
import hu.neuron.ier.business.bugreport.BugReportServiceRemote;
import hu.neuron.ier.business.bugreport.BugReportServiceRemote.Status;
import hu.neuron.ier.business.client.ClientServiceRemote;
import hu.neuron.ier.business.vo.BugMessageVO;
import hu.neuron.ier.business.vo.BugReportVO;
import hu.neuron.ier.business.vo.ClientVO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * Controller for the admin site.
 */
@ViewScoped
@ManagedBean(name = "bugReportApplyController")
public class BugReportApplyController implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@EJB(name = "BugReportService", mappedName = "BugReportService")
	private BugReportServiceRemote bugReportService;
	
	@EJB(name = "BugMessageService", mappedName = "BugMessageService")
	private BugMessageServiceRemote bugMessageService;
	
	@EJB(name = "ClientService", mappedName = "ClientService")
	private ClientServiceRemote clientService;
	
	private String comment;
	
	private LazyBugReportManagementModel lazyBugReportManagementModel;
	
	private BugReportVO selectedBugReport;
	
	private List<BugMessageVO> bugMessageList;

	private String name;

	private String subject;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

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
	
	public void sendBug(){
		FacesContext context = FacesContext.getCurrentInstance();
		BugReportVO bugReportVO;
		ClientVO clientVO;
		try { 
			
		clientVO = new ClientVO();
		
		bugReportVO = new BugReportVO();
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    name = user.getUsername();
		
		clientVO = clientService.findClientByName(name);
		clientVO.setClientId(name);
		
		bugReportVO.setClientId(name);
		bugReportVO.setStatus("NEW");
		bugReportVO.setSubject(subject);
		clientService.saveClient(clientVO);
		bugReportService.saveBugReport(bugReportVO);
		
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_INFO, "Info",
				"Bug Report successful"));
	} catch (Exception e) {
		e.printStackTrace();
		context.addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
	}
		
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