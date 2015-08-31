package hu.neuron.ier.web;

import hu.neuron.ier.business.client.ClientSelfCareServiceRemote;
import hu.neuron.ier.business.client.ClientServiceRemote;
import hu.neuron.ier.business.purchase.PurchaseServiceRemote;
import hu.neuron.ier.business.vo.ClientVO;
import hu.neuron.ier.business.vo.OfferVO;
import hu.neuron.ier.business.vo.PurchaseVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ViewScoped
@ManagedBean(name = "clientSelfCareController")
public class ClientSelfCareController implements Serializable {
	private static final long serialVersionUID = 1L;

	private ClientVO currentClient = new ClientVO();
	private String password1 = null;
	private String password2 = null;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PurchaseVO> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<PurchaseVO> purchases) {
		this.purchases = purchases;
	}

	private String name;
	List<PurchaseVO> purchases;

	@EJB(name = "ClientSelfCareService", mappedName = "ClientSelfCareService")
	private ClientSelfCareServiceRemote clientSelfCareService;
	
	@EJB(name = "PurchaseService", mappedName = "PurchaseService")
	private PurchaseServiceRemote purchaseService;
	
	@EJB(name = "ClientService", mappedName = "ClientService")
	private ClientServiceRemote clientService;

	@PostConstruct
	public void init() {
		currentClient = clientSelfCareService.getClientByName(SecurityContextHolder.getContext()
				.getAuthentication().getName());
	}

	public String updateClient() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (!password1.equals(getPassword2())) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
						"Password not match"));
				return null;
			} else if (clientSelfCareService.getClientByEmail(currentClient.getEmail()) != null
					&& !clientSelfCareService.getClientByEmail(currentClient.getEmail()).getUserName()
							.equals(currentClient.getUserName())) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
						"Email already in used"));
				return null;
			}

			if (!password1.equals("")) {
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				String encPassword = bCryptPasswordEncoder.encode(password1);
				currentClient.setPassword(encPassword);
			}

			if (clientSelfCareService.updateClient(currentClient)) {
				context.getExternalContext().getFlash().setKeepMessages(true);
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"Update successful"));
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		}
		return null;
	}
	
	
	public List<PurchaseVO> createPurchases(){
		try {
			ClientVO clientVO = new ClientVO();
			
			
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			name = user.getUsername();
			
			clientVO = clientService.findClientByName(name);
			
			purchases = new ArrayList<PurchaseVO>();
			purchases = purchaseService.getPurchaseByClient(clientVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return purchases;
	}

	public ClientVO getCurrentClient() {
		return currentClient;
	}

	public void setCurrentClient(ClientVO currentClient) {
		this.currentClient = currentClient;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public ClientSelfCareServiceRemote getClientSelfCareService() {
		return clientSelfCareService;
	}

	public void setClientSelfCareService(ClientSelfCareServiceRemote clientSelfCareService) {
		this.clientSelfCareService = clientSelfCareService;
	}

}