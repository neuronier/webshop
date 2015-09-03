package hu.neuron.ier.business.webservice.vo;


import hu.neuron.ier.business.vo.OrderElementVO;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "WebshopOrder")
public class WebshopOrderWebServiceVO implements Serializable {


	private static final long serialVersionUID = 3129217083180187209L;
	private Long id;
	private Long ordersId;
	private Calendar date;
	private Collection<OrderElementVO> orderElements;
	private String status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public Collection<OrderElementVO> getOrderElements() {
		return orderElements;
	}
	public void setOrderElements(Collection<OrderElementVO> orderElements) {
		this.orderElements = orderElements;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}
	

}
