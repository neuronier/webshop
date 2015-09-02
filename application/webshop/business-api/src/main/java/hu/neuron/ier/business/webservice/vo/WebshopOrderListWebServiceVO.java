package hu.neuron.ier.business.webservice.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "WebshopOrdersList")
public class WebshopOrderListWebServiceVO {

	@XmlElement(name = "orders")
	private List<WebshopOrderWebServiceVO> order;

	public List<WebshopOrderWebServiceVO> getList() {
		return order;
	}

	public void setList(List<WebshopOrderWebServiceVO> order) {
		this.order = order;
	}

}