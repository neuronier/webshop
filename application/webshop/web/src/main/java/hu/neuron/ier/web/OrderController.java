package hu.neuron.ier.web;

import hu.neuron.ier.business.orders.OrdersServiceRemote;
import hu.neuron.ier.business.vo.OrderElementVO;
import hu.neuron.ier.business.vo.OrdersVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;


import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



@ViewScoped
@ManagedBean(name = "orderController")
public class OrderController implements Serializable {

	private static final long serialVersionUID = 4095137963841851711L;
	

	@EJB(name = "OrdersService", mappedName = "OrdersService")
	OrdersServiceRemote ordersService;

	private Long id;
	private Long ordersId;
	private Calendar date;
	private Collection<OrderElementVO> orderElements;
	private String status;
	private List<OrdersVO> orders;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
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
	public List<OrdersVO> getOrders() {
		return orders;
	}
	public void setOrders(List<OrdersVO> orders) {
		this.orders = orders;
	}
	
	public List<OrdersVO> createOrderss(){
		try {
			
			orders = new ArrayList<OrdersVO>();
		        
		        	
		        	orders = ordersService.getAllOrders();
		        	
		            
		            
		        
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return orders;
		
       
            
	}
}
