package hu.neuron.ier.web;

import hu.neuron.ier.business.orderelement.OrderElementServiceRemote;
import hu.neuron.ier.business.orders.OrdersServiceRemote;
import hu.neuron.ier.business.producttype.ProductTypeServiceRemote;
import hu.neuron.ier.business.vo.OrderElementVO;
import hu.neuron.ier.business.vo.OrdersVO;
import hu.neuron.ier.business.vo.ProductTypeVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ViewScoped
@ManagedBean(name = "orderController")
public class OrderController implements Serializable {
	
	private Long id;
	private String status;
	private Calendar date;

	@ManagedProperty("#{orderSet}")
    private OrderSessionBean orderSet;

	public OrderSessionBean getOrderSet() {
		return orderSet;
	}

	public void setOrderSet(OrderSessionBean orderSet) {
		this.orderSet = orderSet;
	}

	private static final long serialVersionUID = 4095137963841851711L;

	@EJB(name = "OrdersService", mappedName = "OrdersService")
	OrdersServiceRemote ordersService;
	
	@EJB(name = "OrderElementService", mappedName = "OrderElementService")
	OrderElementServiceRemote orderElementService;
	
	@EJB(name = "ProductTypeService", mappedName = "ProductTypeService")
	ProductTypeServiceRemote productTypeService;
	
	
	
	private List<ProductTypeVO> productTypes = new ArrayList<ProductTypeVO>();
	private List<OrderElementVO> orderElements = new ArrayList<OrderElementVO>();
	private List<OrdersVO> orders = new ArrayList<OrdersVO>();
	
	private List<OrdersVO> selectedOrders;
	
	
	private ProductTypeVO selectedProduct;
	private OrderElementVO selectedElement;
	
	public void updateOrders() throws Exception {
		orders.clear();
		orders.addAll(ordersService.getAllOrders());
	}

	public List<OrdersVO> getOrders() throws Exception {
		this.updateOrders();
		return orders;
	}

	public void setOrders(List<OrdersVO> orders) {
		this.orders = orders;
	}
	
	
	public void updateProductTypes() throws Exception {
		productTypes.clear();
		productTypes.addAll(productTypeService.getAllProductType());
	}

	public List<ProductTypeVO> getProducts() throws Exception {
		this.updateProductTypes();
		return productTypes;
	}

	public void setProducts(List<ProductTypeVO> productTypes) {
		this.productTypes = productTypes;
	}
	
	
	
	
	

	public OrdersVO getSelectedOrder() {
		return orderSet.getSettedOrder();
	}

	public void setSelectedOrder(OrdersVO selectedOrder) {
		this.orderSet.setSettedOrder(selectedOrder);
	}

	public List<OrdersVO> getSelectedOrders() {
		return selectedOrders;
	}

	public void setSelectedCars(List<OrdersVO> selectedOrders) {
		this.selectedOrders = selectedOrders;
	}
	
	public void deleteSelected() {
		FacesContext context = FacesContext.getCurrentInstance();
		OrdersVO orderVO = getSelectedOrder();
		try {
			ordersService.deleteOrders(orderVO.getId());
			orderSet.setSettedOrder(null);
			updateOrders();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addOrder(){
		OrdersVO orderVO = new OrdersVO();
		orderVO.setOrdersId(id);
		orderVO.setStatus(status);
		try {
			ordersService.createOrder(orderVO);
			updateOrders();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}
	
	

	public void setSelectedOrders(List<OrdersVO> selectedOrders) {
		this.selectedOrders = selectedOrders;
	}

	public List<OrderElementVO> getOrderElements() {
		return orderElements;
	}

	public void setOrderElements(List<OrderElementVO> orderElements) {
		this.orderElements = orderElements;
	}

	public List<ProductTypeVO> getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(List<ProductTypeVO> productTypes) {
		this.productTypes = productTypes;
	}

	public ProductTypeVO getSelectedProduct() {
		return selectedProduct;
	}
	
	public void setSelectedProduct(ProductTypeVO selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public OrderElementVO getSelectedElement() {
		return selectedElement;
	}

	public void setSelectedElement(OrderElementVO selectedElement) {
		this.selectedElement = selectedElement;
	}
	
	
}