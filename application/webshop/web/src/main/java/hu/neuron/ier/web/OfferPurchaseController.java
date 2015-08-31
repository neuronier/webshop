package hu.neuron.ier.web;

import hu.neuron.ier.business.client.ClientServiceRemote;
import hu.neuron.ier.business.offer.OfferServiceRemote;
import hu.neuron.ier.business.purchase.PurchaseServiceRemote;
import hu.neuron.ier.business.vo.ClientVO;
import hu.neuron.ier.business.vo.OfferVO;
import hu.neuron.ier.business.vo.PurchaseVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * Controller for the admin site.
 */

@ManagedBean(name = "offerPurchaseController")
@ApplicationScoped
public class OfferPurchaseController implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	List<OfferVO> list;
	List<PurchaseVO> purchases;
	private Long id;
	private Long newCost;
	private Long originalCost;
	private String name;
	private String description;
	private boolean action;
	private boolean featured;
	private OfferVO selectedClientOffer;
	
	@EJB(name = "OfferService", mappedName = "OfferService")
	private OfferServiceRemote offerService;
	
	@EJB(name = "PurchaseService", mappedName = "PurchaseService")
	private PurchaseServiceRemote purchaseService;
	
	@EJB(name = "ClientService", mappedName = "ClientService")
	private ClientServiceRemote clientService;
	
	public List<OfferVO> createPurchasedOffers(Long purchaseId) {
		try {
			String name;
			ClientVO clientVO = new ClientVO();
			
			list = new ArrayList<OfferVO>();
			
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		    name = user.getUsername();
			
			clientVO = clientService.findClientByName(name);
			
			
			
//			purchases = new ArrayList<PurchaseVO>();
			purchases = purchaseService.getPurchaseByClient(clientVO);
			
			PurchaseVO purchaseVO = purchaseService.getPurchaseById(purchaseId);
			
//			for (PurchaseVO purchaseVO : purchases) {
//				offerVos = offerService.getOffersFromPurchase(purchaseVO);
//				list.addAll(offerVos);
//			}
			
			list = offerService.getOffersFromPurchase(purchaseVO);
			
			} catch (Exception e) {

		}
		return list;

	}

	public void onRowSelect(SelectEvent event) {
		
		
		selectedClientOffer = (OfferVO) event.getObject();
		
		FacesContext.getCurrentInstance().addMessage(
				"createmsgs",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						selectedClientOffer.getName()));
	}

	public List<OfferVO> getOffers() {
		return list;
	}

	public void setOffers(List<OfferVO> offers) {
		this.list = offers;
	}

	public OfferServiceRemote getOfferService() {
		return offerService;
	}

	public void setOfferService(OfferServiceRemote offerService) {
		this.offerService = offerService;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNewCost() {
		return newCost;
	}

	public void setNewCost(Long newCost) {
		this.newCost = newCost;
	}

	public Long getOriginalCost() {
		return originalCost;
	}

	public void setOriginalCost(Long originalCost) {
		this.originalCost = originalCost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAction() {
		return action;
	}

	public void setAction(boolean action) {
		this.action = action;
	}

	public boolean isFeatured() {
		return featured;
	}

	public void setFeatured(boolean featured) {
		this.featured = featured;
	}

	public void onRowUnselect(UnselectEvent event) {
		selectedClientOffer = null;
	}

	public void unselect() {
		selectedClientOffer = null;
	}

	public OfferVO getSelectedOffer() {
		return selectedClientOffer;
	}

	public void setSelectedOffer(OfferVO selectedOffer) {
		this.selectedClientOffer = selectedOffer;
	}

	public List<OfferVO> getList() {
		return list;
	}

	public void setList(List<OfferVO> list) {
		this.list = list;
	}


}
