package hu.neuron.ier.business.webservice.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BugMessageList")
public class BugMessageListWebServiceVO {

	@XmlElement(name = "bugMessages")
	private List<BugMessageWebServiceVO> list;

	public List<BugMessageWebServiceVO> getList() {
		return list;
	}

	public void setList(List<BugMessageWebServiceVO> list) {
		this.list = list;
	}

}
