package hu.neuron.ier.web;

import hu.neuron.ier.business.address.AddressServiceRemote;
import hu.neuron.ier.business.client.ClientServiceRemote;
import hu.neuron.ier.business.purchase.PurchaseServiceRemote;
import hu.neuron.ier.business.vo.AddressVO;
import hu.neuron.ier.business.vo.ClientVO;
import hu.neuron.ier.business.vo.OfferVO;
import hu.neuron.ier.business.vo.PurchaseVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.springframework.security.core.context.SecurityContextHolder;

@ViewScoped
@ManagedBean(name = "paymentController")
public class PaymentController implements Serializable {

	private static final long serialVersionUID = 3397706819780203623L;

	@ManagedProperty("#{shoppingCartController}")
	private ShoppingCartController shoppingCartController;

	@EJB(mappedName = "ClientService", name = "ClientService")
	private ClientServiceRemote clientService;

	@EJB(mappedName = "PurchaseService", name = "PurchaseService")
	PurchaseServiceRemote purchaseService;

	@EJB(mappedName = "AddressService", name = "AddressService")
	AddressServiceRemote addressService;

	private ClientVO currentClient = new ClientVO();
	private String clientFullName;
	private String clientPhone;
	private String clientEmail;
	private AddressVO clientBillingAddress;
	private AddressVO clientDeliveryAddress;

	private long billingPostcode;
	private String billingCity;
	private String billingStreet;
	private String billingHouse;
	private long deliveryPostcode;
	private String deliveryCity;
	private String deliveryStreet;
	private String deliveryHouse;

	private boolean addressSame;

	@PostConstruct
	public void init() {
		currentClient = clientService.findClientByName(SecurityContextHolder.getContext()
				.getAuthentication().getName());
		clientFullName = currentClient.getFullName();
		clientPhone = currentClient.getPhone();
		clientEmail = currentClient.getEmail();
		clientBillingAddress = currentClient.getBillingAddress();
		clientDeliveryAddress = currentClient.getDeliveryAddress();

		billingCity = clientBillingAddress.getCity();
		billingHouse = clientBillingAddress.getHouse();
		billingPostcode = clientBillingAddress.getPostcode();
		billingStreet = clientBillingAddress.getStreet();

		deliveryCity = clientDeliveryAddress.getCity();
		deliveryHouse = clientDeliveryAddress.getHouse();
		deliveryPostcode = clientDeliveryAddress.getPostcode();
		deliveryStreet = clientDeliveryAddress.getStreet();
	}

	public int totalPrice() {
		List<OfferVO> offers = new ArrayList<OfferVO>();
		offers.addAll(shoppingCartController.getOffers());
		int price = 0;
		for (OfferVO offerVO : offers) {
			price += offerVO.getNewCost();
		}

		return price;
	}

	public void paying() {
		saveClientData();

		PurchaseVO purchaseVO = new PurchaseVO();
		purchaseVO.setClient(currentClient);
		purchaseVO.setDate(Calendar.getInstance());
		purchaseVO.setFullCost(Integer.valueOf(totalPrice()).longValue());
		try {
			purchaseVO = purchaseService.createPurchase(purchaseVO);
			Map<OfferVO, Long> map = new HashMap<OfferVO, Long>();
			for (OfferVO offerVO : shoppingCartController.getOffers()) {
				map.put(offerVO, shoppingCartController.offerCounter(offerVO));
			}
			purchaseService.addOffersToPurchace(map, purchaseVO);
			shoppingCartController.deleteAllOfferFromShoppingCart();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void saveClientData() {
		currentClient.setFullName(clientFullName);
		currentClient.setEmail(clientEmail);
		currentClient.setPhone(clientPhone);
		clientDeliveryAddress.setCity(deliveryCity);
		clientDeliveryAddress.setHouse(deliveryHouse);
		clientDeliveryAddress.setPostcode(deliveryPostcode);
		clientDeliveryAddress.setStreet(deliveryStreet);

		if (!addressSame) {
			clientBillingAddress.setCity(clientDeliveryAddress.getCity());
			clientBillingAddress.setHouse(clientDeliveryAddress.getHouse());
			clientBillingAddress.setPostcode(clientDeliveryAddress.getPostcode());
			clientBillingAddress.setStreet(clientDeliveryAddress.getStreet());
		} else {
			clientBillingAddress.setCity(billingCity);
			clientBillingAddress.setHouse(billingHouse);
			clientBillingAddress.setPostcode(billingPostcode);
			clientBillingAddress.setStreet(billingStreet);
		}
		try {
			clientBillingAddress = addressService.updateAddress(clientBillingAddress);
			clientDeliveryAddress = addressService.updateAddress(clientDeliveryAddress);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		currentClient.setBillingAddress(clientBillingAddress);
		currentClient.setDeliveryAddress(clientDeliveryAddress);

		currentClient = clientService.registrationClient(currentClient);
	}

	public ShoppingCartController getShoppingCartController() {
		return shoppingCartController;
	}

	public void setShoppingCartController(ShoppingCartController shoppingCartController) {
		this.shoppingCartController = shoppingCartController;
	}

	public ClientServiceRemote getClientService() {
		return clientService;
	}

	public void setClientService(ClientServiceRemote clientService) {
		this.clientService = clientService;
	}

	public PurchaseServiceRemote getPurchaseService() {
		return purchaseService;
	}

	public void setPurchaseService(PurchaseServiceRemote purchaseService) {
		this.purchaseService = purchaseService;
	}

	public ClientVO getCurrentClient() {
		return currentClient;
	}

	public void setCurrentClient(ClientVO currentClient) {
		this.currentClient = currentClient;
	}

	public String getClientFullName() {
		return clientFullName;
	}

	public void setClientFullName(String clientFullName) {
		this.clientFullName = clientFullName;
	}

	public String getClientPhone() {
		return clientPhone;
	}

	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public AddressVO getClientBillingAddress() {
		return clientBillingAddress;
	}

	public void setClientBillingAddress(AddressVO clientBillingAddress) {
		this.clientBillingAddress = clientBillingAddress;
	}

	public AddressVO getClientDeliveryAddress() {
		return clientDeliveryAddress;
	}

	public void setClientDeliveryAddress(AddressVO clientDeliveryAddress) {
		this.clientDeliveryAddress = clientDeliveryAddress;
	}

	public AddressServiceRemote getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressServiceRemote addressService) {
		this.addressService = addressService;
	}

	public long getBillingPostcode() {
		return billingPostcode;
	}

	public void setBillingPostcode(long billingPostcode) {
		this.billingPostcode = billingPostcode;
	}

	public String getBillingCity() {
		return billingCity;
	}

	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	public String getBillingStreet() {
		return billingStreet;
	}

	public void setBillingStreet(String billingStreet) {
		this.billingStreet = billingStreet;
	}

	public String getBillingHouse() {
		return billingHouse;
	}

	public void setBillingHouse(String billingHouse) {
		this.billingHouse = billingHouse;
	}

	public long getDeliveryPostcode() {
		return deliveryPostcode;
	}

	public void setDeliveryPostcode(long deliveryPostcode) {
		this.deliveryPostcode = deliveryPostcode;
	}

	public String getDeliveryCity() {
		return deliveryCity;
	}

	public void setDeliveryCity(String deliveryCity) {
		this.deliveryCity = deliveryCity;
	}

	public String getDeliveryStreet() {
		return deliveryStreet;
	}

	public void setDeliveryStreet(String deliveryStreet) {
		this.deliveryStreet = deliveryStreet;
	}

	public String getDeliveryHouse() {
		return deliveryHouse;
	}

	public void setDeliveryHouse(String deliveryHouse) {
		this.deliveryHouse = deliveryHouse;
	}

	public boolean isAddressSame() {
		return addressSame;
	}

	public void setAddressSame(boolean addressSame) {
		this.addressSame = addressSame;
	}

}
