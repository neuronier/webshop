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
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ViewScoped
@ManagedBean(name = "orderChangeController")
public class OrderChangeController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8867547966125627252L;
	
	
	private List<ProductTypeVO> productTypes = new ArrayList<ProductTypeVO>();
	private int darab;
	private List<OrderElementVO> actualElements = new ArrayList<OrderElementVO>();
	private List<OrderElementVO> orderElements = new ArrayList<OrderElementVO>();
	private OrderElementVO element = new OrderElementVO();
	private ProductTypeVO selectedProduct;
	private OrderElementVO selectedElement;
	private OrdersVO newOrderVO;
	
	@PostConstruct
	public void initOrder() throws Exception {
		newOrderVO = getSelectedOrder();
		orderElements = ((List<OrderElementVO>) newOrderVO.getOrderElements());
		actualElements.clear();
		for(OrderElementVO element : orderElements){
			OrderElementVO orderElementVO = new OrderElementVO();
			orderElementVO.setProductType(element.getProductType());
			orderElementVO.setQuanty(element.getQuanty());
			actualElements.add(orderElementVO);
		}
		
	}
	
	public void updateProductTypes() throws Exception {
		productTypes.clear();
		productTypes.addAll(productTypeService.getAllProductType());
	}

	public void setProductTypes(List<ProductTypeVO> productTypes) {
		this.productTypes = productTypes;
	}

	public List<ProductTypeVO> getProductTypes() throws Exception {
		this.updateProductTypes();
		return productTypes;
	}

	public void setProducts(List<ProductTypeVO> productTypes) {
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

	public int getDarab() {
		return darab;
	}

	public void setDarab(int darab) {
		this.darab = darab;
	}

	public OrderElementVO getElement() {
		return element;
	}

	public void setElement(OrderElementVO element) {
		this.element = element;
	}

	@EJB(name = "OrdersService", mappedName = "OrdersService")
	OrdersServiceRemote ordersService;
	
	@EJB(name = "OrderElementService", mappedName = "OrderElementService")
	OrderElementServiceRemote orderElementService;
	
	@EJB(name = "ProductTypeService", mappedName = "ProductTypeService")
	ProductTypeServiceRemote productTypeService;
	
	@ManagedProperty("#{orderSet}")
    private OrderSessionBean orderSet;

	public OrderSessionBean getOrderSet() {
		return orderSet;
	}

	public void setOrderSet(OrderSessionBean orderSet) {
		this.orderSet = orderSet;
	}
	
	public void addItem(ProductTypeVO productVO) throws Exception{
		boolean van=false;
		for(OrderElementVO element : actualElements){
			if(element.getProductType().getItemNumber().equals(productVO.getItemNumber())){
				van=true;
			}
		}
		if(van==false){
			OrderElementVO orderElement =new OrderElementVO();
			orderElement.setProductType(productVO);
			orderElement.setQuanty(darab);
			actualElements.add(orderElement);
		}
		selectedProduct = null;
		selectedElement=null;
		darab=0;
	}
	
	public void deleteSelectedElement(OrderElementVO orderElement){
		try {
			for(OrderElementVO element : actualElements){
				if(element.getProductType().getItemNumber().equals(orderElement.getProductType().getItemNumber())){
					actualElements.remove(element);
				}
			}
			selectedElement=null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addOrder() throws Exception{
		ordersService.deleteOrders(newOrderVO.getId());
		for(OrderElementVO element : orderElements){
			orderElementService.deleteOrderElement(element.getId());
		}
		List<OrderElementVO> orders = new ArrayList<OrderElementVO>();
		OrderElementVO orderE = new OrderElementVO();
		OrdersVO orderVO = new OrdersVO();
		Calendar cal = Calendar.getInstance();
		System.out.println("Current year is :" + cal.get(Calendar.YEAR));
		orderVO.setDate(cal);
		orderVO.setStatus("Új");
		orderVO.setOrdersId(newOrderVO.getOrdersId());
		try {
			for(OrderElementVO element : actualElements){
				orderE = orderElementService.createOrderElement(element);
				orders.add(orderE);
			}
			orderVO.setOrderElements(orders);

			orderVO = ordersService.createOrder(orderVO);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actualElements.clear();
		orderElements.clear();
	}
	
	public OrdersVO getSelectedOrder() {
		return orderSet.getSettedOrder();
	}

	public void setSelectedOrder(OrdersVO selectedOrder) {
		this.orderSet.setSettedOrder(selectedOrder);
	}
	
	public List<OrderElementVO> getActualElements() {
		return actualElements;
	}

	public void setActualElements(List<OrderElementVO> actualElements) {
		this.actualElements = actualElements;
	}

	public List<OrderElementVO> getOrderElements() {
		return orderElements;
	}

	public void setOrderElements(List<OrderElementVO> orderElements) {
		this.orderElements = orderElements;
	}
	
}