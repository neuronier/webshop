package hu.neuron.ier.web;

import hu.neuron.ier.business.client.ClientServiceRemote;
import hu.neuron.ier.business.purchase.PurchaseServiceRemote;
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
	private ClientServiceRemote clientSelfCareService;

	@EJB(mappedName = "PurchaseService", name = "PurchaseService")
	PurchaseServiceRemote purchaseService;

	private ClientVO currentClient = new ClientVO();
	private String clientFullName;
	private String clientPhone;
	private String clientEmail;
	private String clientBillingAddress;
	private String clientDeliveryAddress;

	@PostConstruct
	public void init() {
		currentClient = clientSelfCareService.findClientByName(SecurityContextHolder.getContext()
				.getAuthentication().getName());
		clientFullName = currentClient.getFullName();
		clientPhone = currentClient.getPhone();
		clientEmail = currentClient.getEmail();
		clientBillingAddress = currentClient.getBillingAddress().toString();
		clientDeliveryAddress = currentClient.getDeliveryAddress().toString();
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
		PurchaseVO purchaseVO = new PurchaseVO();
		purchaseVO.setClientVO(currentClient);
		purchaseVO.setDate(Calendar.getInstance());
		purchaseVO.setFullCost(Integer.valueOf(totalPrice()).longValue());
		try {
			purchaseVO = purchaseService.createPurchase(purchaseVO);
			Map<OfferVO, Long> map = new HashMap<OfferVO, Long>();
			for (OfferVO offerVO : shoppingCartController.getOffers()) {
				map.put(offerVO, shoppingCartController.offerCounter(offerVO));
			}
			purchaseService.addOffersToPurchace(map, purchaseVO);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public ShoppingCartController getShoppingCartController() {
		return shoppingCartController;
	}

	public void setShoppingCartController(ShoppingCartController shoppingCartController) {
		this.shoppingCartController = shoppingCartController;
	}

	public ClientServiceRemote getClientSelfCareService() {
		return clientSelfCareService;
	}

	public void setClientSelfCareService(ClientServiceRemote clientSelfCareService) {
		this.clientSelfCareService = clientSelfCareService;
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

	public String getClientBillingAddress() {
		return clientBillingAddress;
	}

	public void setClientBillingAddress(String clientBillingAddress) {
		this.clientBillingAddress = clientBillingAddress;
	}

	public String getClientDeliveryAddress() {
		return clientDeliveryAddress;
	}

	public void setClientDeliveryAddress(String clientDeliveryAddress) {
		this.clientDeliveryAddress = clientDeliveryAddress;
	}

}
