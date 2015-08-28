package hu.neuron.ier.web;

import hu.neuron.ier.business.orderelement.OrderElementServiceRemote;
import hu.neuron.ier.business.orders.OrdersServiceRemote;
import hu.neuron.ier.business.producttype.ProductTypeServiceRemote;
import hu.neuron.ier.business.vo.OrderElementVO;
import hu.neuron.ier.business.vo.OrdersVO;
import hu.neuron.ier.business.vo.ProductTypeVO;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


@ViewScoped
@ManagedBean(name = "orderController")
public class OrderController implements Serializable {
	
	private int darab;
	private Long id;
	private String status;
	private Calendar date;
	private List<String> statusMenu = new ArrayList<String>();

	@ManagedProperty("#{orderSet}")
    private OrderSessionBean orderSet;

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
	private List<OrdersVO> filteredOrders;
	
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

	public List<ProductTypeVO> getProductTypes() throws Exception {
		this.updateProductTypes();
		return productTypes;
	}

	public void setProducts(List<ProductTypeVO> productTypes) {
		this.productTypes = productTypes;
	}

	public List<OrderElementVO> getOrderElements() throws Exception {
		return orderElements;
	}

	public OrdersVO getSelectedOrder() {
		return orderSet.getSettedOrder();
	}

	public void setSelectedOrder(OrdersVO selectedOrder) {
		this.orderSet.setSettedOrder(selectedOrder);
	}
	
	@PostConstruct
	public void initOrder() {
		orderElements = new ArrayList<OrderElementVO>();
		statusMenu.add("Új");
		statusMenu.add("Kész");
		statusMenu.add("Folyamatban");
	}
	
	public void addItem(ProductTypeVO productVO) throws Exception{
		boolean van=false;
		for(OrderElementVO element : orderElements){
			if(element.getProductType().getItemNumber().equals(productVO.getItemNumber())){
				van=true;
			}
		}
		if(van==false){
			OrderElementVO orderElement =new OrderElementVO();
			orderElement.setProductType(productVO);
			orderElement.setQuanty(darab);
			orderElements.add(orderElement);
		}
		selectedProduct = null;
		selectedElement=null;
		darab=0;
	}
	
	public void addOrder() throws Exception{
		List<OrderElementVO> orders = new ArrayList<OrderElementVO>();
		OrderElementVO orderE = new OrderElementVO();
		OrdersVO orderVO = new OrdersVO();
		Calendar cal = Calendar.getInstance();
		System.out.println("Current year is :" + cal.get(Calendar.YEAR));
		orderVO.setDate(cal);
		orderVO.setStatus("Új");
		try {
			for(OrderElementVO element : orderElements){
				orderE = orderElementService.createOrderElement(element);
				orders.add(orderE);
			}
			orderVO.setOrderElements(orders);

			ordersService.createOrder(orderVO);
			updateOrders();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		orderElements.clear();
	}
	
	public void deleteSelectedElement(OrderElementVO orderElement){
		try {
			for(OrderElementVO element : orderElements){
				if(element.getProductType().getItemNumber().equals(orderElement.getProductType().getItemNumber())){
					orderElements.remove(element);
				}
			}
			selectedElement=null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void deleteSelected() {
		OrdersVO orderVO = getSelectedOrder();
		try {
			
			ordersService.deleteOrders(orderVO.getId());
			orderSet.setSettedOrder(null);
			for(OrderElementVO element : orderElements){
				orderElementService.deleteOrderElement(element.getId());
			}
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


	public void setOrderElements(List<OrderElementVO> orderElements) {
		this.orderElements = orderElements;
	}

	public void setProductTypes(List<ProductTypeVO> productTypes){
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
	
	public OrderSessionBean getOrderSet() {
		return orderSet;
	}

	public void setOrderSet(OrderSessionBean orderSet) {
		this.orderSet = orderSet;
	}

	public List<String> getStatusMenu() {
		return statusMenu;
	}

	public void setStatusMenu(List<String> statusMenu) {
		this.statusMenu = statusMenu;
	}

	public int getDarab() {
		return darab;
	}

	public void setDarab(int darab) {
		this.darab = darab;
	}

	public List<OrdersVO> getFilteredOrders() {
		return filteredOrders;
	}

	public void setFilteredOrders(List<OrdersVO> filteredOrders) {
		this.filteredOrders = filteredOrders;
	}
	
	public String dateToString(Calendar date){
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss");
		return dateFormat.format(date.getTime());
	}
}
