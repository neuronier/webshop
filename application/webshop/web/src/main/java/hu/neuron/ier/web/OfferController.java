package hu.neuron.ier.web;

import hu.neuron.ier.business.offer.OfferServiceRemote;
import hu.neuron.ier.business.vo.OfferVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 * Controller for the admin site.
 */

@ManagedBean(name = "offerController")
@ApplicationScoped
public class OfferController implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	List<OfferVO> list;
	private Long id;
	private Long newCost;
	private Long originalCost;
	private String name;
	private String description;
	private boolean action;
	private LazyOfferModel lazyOfferModel;
	private boolean featured;
	private OfferVO selectedOffer;
	
	

	@EJB(name = "OfferService", mappedName = "OfferService")
	private OfferServiceRemote offerService;

	@PostConstruct
	public void init() {

		setLazyOfferModel(new LazyOfferModel(getOfferService()));
	}
	

	public List<OfferVO> createOffers() {
		try {

			list = new ArrayList<OfferVO>();

			list = offerService.getFeaturedOffers(true);

		} catch (Exception e) {

		}
		return list;

	}

	public void updateOffer() {
		try {
			selectedOffer.setFeatured(featured);
			selectedOffer.setAction(action);
			selectedOffer.setNewCost(newCost);
			offerService.saveOffer(selectedOffer);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Update: " + selectedOffer.getName()));
			selectedOffer = null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Update: "));
		}
	}

	public void onRowSelect(SelectEvent event) {
		
		
		selectedOffer = (OfferVO) event.getObject();
		
		FacesContext.getCurrentInstance().addMessage(
				"createmsgs",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						selectedOffer.getName()));
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
		selectedOffer = null;
	}

	public void unselect() {
		selectedOffer = null;
	}

	public OfferVO getSelectedOffer() {
		return selectedOffer;
	}

	public void setSelectedOffer(OfferVO selectedOffer) {
		this.selectedOffer = selectedOffer;
	}

	public List<OfferVO> getList() {
		return list;
	}

	public void setList(List<OfferVO> list) {
		this.list = list;
	}

	public LazyOfferModel getLazyOfferModel() {
		return lazyOfferModel;
	}

	public void setLazyOfferModel(LazyOfferModel lazyOfferModel) {
		this.lazyOfferModel = lazyOfferModel;
	}
}
