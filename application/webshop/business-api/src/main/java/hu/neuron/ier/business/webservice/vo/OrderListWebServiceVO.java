package hu.neuron.ier.business.webservice.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OrderList")
public class OrderListWebServiceVO {

	@XmlElement(name = "orders")
	private List<OrderWebServiceVO> order;

	public List<OrderWebServiceVO> getList() {
		return order;
	}

	public void setList(List<OrderWebServiceVO> order) {
		this.order = order;
	}

}