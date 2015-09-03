package hu.neuron.ier.business.webservice.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BugReportList")
public class BugReportListWebServiceVO {

	@XmlElement(name = "bugReports")
	private List<BugReportWebServiceVO> list;

	public List<BugReportWebServiceVO> getList() {
		return list;
	}

	public void setList(List<BugReportWebServiceVO> list) {
		this.list = list;
	}

}
