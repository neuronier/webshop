package hu.neuron.ier.core.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "BugReport")
@NamedQueries({
		@NamedQuery(name = "BugReport.findByReportId", query = "SELECT it FROM BugReport it  WHERE it.reportId = :reportId"),
		@NamedQuery(name = "BugReport.findByClientId", query = "SELECT it FROM BugReport it  WHERE it.clientId = :clientId")
})
public class BugReport extends BaseEntity{

	private static final long serialVersionUID = 9159421569896952603L;

	private String reportId;
	
	private String clientId;
	
	private String status;
	
	private String subject;
	
	private Date lastUpdate;

	public BugReport() {
		super();
		this.reportId = UUID.randomUUID().toString();
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String threadID) {
		this.reportId = threadID;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientID) {
		this.clientId = clientID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	

	
}
