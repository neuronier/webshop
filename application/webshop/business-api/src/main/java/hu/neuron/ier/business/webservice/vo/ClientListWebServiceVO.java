package hu.neuron.ier.business.webservice.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ClientList")
public class ClientListWebServiceVO {

	@XmlElement(name = "clients")
	private List<ClientWebServiceVO> list;

	public List<ClientWebServiceVO> getList() {
		return list;
	}

	public void setList(List<ClientWebServiceVO> list) {
		this.list = list;
	}

}
