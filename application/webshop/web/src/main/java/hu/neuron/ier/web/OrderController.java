package hu.neuron.ier.web;

import hu.neuron.ier.business.orders.OrdersServiceRemote;

import hu.neuron.ier.business.vo.OrdersVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ViewScoped
@ManagedBean(name = "orderController")
public class OrderController implements Serializable {

	private static final long serialVersionUID = 4095137963841851711L;

	@EJB(name = "OrdersService", mappedName = "OrdersService")
	OrdersServiceRemote ordersService;

	private List<OrdersVO> orders = new ArrayList<OrdersVO>();
	
	private List<OrdersVO> selectedOrders;
	private OrdersVO selectedOrder;

	public void updateOrders() throws Exception {
		orders.addAll(ordersService.getAllOrders());
	}

	public List<OrdersVO> getOrders() throws Exception {
		this.updateOrders();
		return orders;
	}

	public void setOrders(List<OrdersVO> orders) {
		this.orders = orders;
	}
 
    public OrdersVO getSelectedOrder() {
        return selectedOrder;
    }
 
    public void setSelectedOrder(OrdersVO selectedOrder) {
        this.selectedOrder = selectedOrder;
    }
 
    public List<OrdersVO> getSelectedOrders() {
        return selectedOrders;
    }
 
    public void setSelectedCars(List<OrdersVO> selectedOrders) {
        this.selectedOrders = selectedOrders;
    }
    
    public void onRowSelect(SelectEvent event) {
    	String id = String.valueOf(((OrdersVO) event.getObject()).getId());
        FacesMessage msg = new FacesMessage("Car Selected", id);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
    	String id = String.valueOf(((OrdersVO) event.getObject()).getId());
        FacesMessage msg = new FacesMessage("Car Unselected", id);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
