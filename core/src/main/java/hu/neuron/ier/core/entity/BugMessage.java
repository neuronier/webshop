package hu.neuron.ier.core.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "BugMessage")
@NamedQueries({
		@NamedQuery(name = "IssueMessage.findByMessageId", query = "SELECT im FROM BugMessage im  WHERE im.messageId = :messageId"),
		@NamedQuery(name = "IssueMessage.findByReportId", query = "SELECT im FROM BugMessage im  WHERE im.reportId = :reportId")
})
public class BugMessage extends BaseEntity{

	private static final long serialVersionUID = -5182968180489611694L;

	private String messageId;
	
	@Column(length = 5000)
	private String text;
	
	private Date date;
	
	private String reportId;
	
	private String owner;

	public BugMessage() {
		super();
		this.messageId = UUID.randomUUID().toString();
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}