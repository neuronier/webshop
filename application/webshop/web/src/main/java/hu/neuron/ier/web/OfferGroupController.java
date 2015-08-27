package hu.neuron.ier.web;

import hu.neuron.ier.business.offer.OfferServiceRemote;
import hu.neuron.ier.business.offergroup.OfferGroupServiceRemote;
import hu.neuron.ier.business.vo.OfferGroupVO;
import hu.neuron.ier.business.vo.OfferVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import org.springframework.ui.context.Theme;

/**
 * Vezérlő osztály az ajánlatcsoporttal kapcsolatos funkciókhoz.
 * 
 * @author Norbert
 *
 */
@ViewScoped
@FacesConverter("offerVoConverter")
@ManagedBean(name = "offerGroupController")
public class OfferGroupController implements Serializable, Converter {

	private static final long serialVersionUID = 7624392370791189825L;

	private String name = "";
	private String description = "";
	private Boolean active;
	private List<OfferGroupVO> allOfferGroup = null;
	private OfferGroupVO selectedOfferGroup = null;
	private DualListModel<OfferVO> offers = null;
	private List<OfferVO> offerSource;
	private List<OfferVO> offerTarget;

	public List<OfferVO> getOfferSource() {
		return offerSource;
	}

	public void setOfferSource(List<OfferVO> offerSource) {
		this.offerSource = offerSource;
	}

	public List<OfferVO> getOfferTarget() {
		return offerTarget;
	}

	public void setOfferTarget(List<OfferVO> offerTarget) {
		this.offerTarget = offerTarget;
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

	public List<OfferGroupVO> getAllOfferGroup() {
		return allOfferGroup;
	}

	public void setAllOfferGroup(List<OfferGroupVO> allOfferGroup) {
		this.allOfferGroup = allOfferGroup;
	}

	public OfferGroupVO getSelectedOfferGroup() {
		return selectedOfferGroup;
	}

	public void setSelectedOfferGroup(OfferGroupVO selectedOfferGroup) {
		this.selectedOfferGroup = selectedOfferGroup;
	}

	public DualListModel<OfferVO> getOffers() {
		return offers;
	}

	public void setOffers(DualListModel<OfferVO> offers) {
		this.offers = offers;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}


	@EJB(name = "OfferGroupService", mappedName = "OfferGroupService")
	OfferGroupServiceRemote offerGroupService;

	@EJB(name = "OfferService", mappedName = "OfferService")
	OfferServiceRemote offerService;

	public void createOfferGroup() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			OfferGroupVO offerGroupVO = new OfferGroupVO();
			offerGroupVO.setName(getName());
			offerGroupVO.setDescription(getDescription());
			offerGroupVO.setActive(false);
			offerGroupService.createOfferGroup(offerGroupVO);
			this.name = "";
			this.description = "";
			getAll();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, null, "Sikertelen mentés!"));

			e.printStackTrace();
		}
	}

	public List<OfferGroupVO> getAll() {
		FacesContext context = FacesContext.getCurrentInstance();
		allOfferGroup = new ArrayList<OfferGroupVO>();
		try {
			allOfferGroup = offerGroupService.findAllOfferGroup();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Error!",
					"Sikertelen listázás!"));
			e.printStackTrace();
		}
		return allOfferGroup;
	}

	public void updateSelected() {
		FacesContext context = FacesContext.getCurrentInstance();
		OfferGroupVO offerGroupVO = getSelectedOfferGroup();
		offerGroupVO.setName(getName());
		offerGroupVO.setDescription(getDescription());
		try {
			offerGroupService.createOfferGroup(offerGroupVO);
			setName("");
			setDescription("");
			selectedOfferGroup = null;
			getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteSelected() {
		FacesContext context = FacesContext.getCurrentInstance();
		OfferGroupVO offerGroupVO = getSelectedOfferGroup();
		try {
			offerGroupService.deleteOfferGroup(offerGroupVO.getId());
			selectedOfferGroup = null;
			getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void initOffers() {
		FacesContext context = FacesContext.getCurrentInstance();
		// Offers
		offerSource = new ArrayList<OfferVO>();
		offerTarget = new ArrayList<OfferVO>();
		offers = new DualListModel<OfferVO>(offerSource, offerTarget);
	}

	public void setupOffers() {
		try {
			offerSource = offerService.getOffersByParentOfferGroup(-1L);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			offerTarget = offerService
					.getOffersByParentOfferGroup(selectedOfferGroup.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		offers = new DualListModel<OfferVO>(offerSource, offerTarget);

	}

	public void addOffers() {
		FacesContext context = FacesContext.getCurrentInstance();
		OfferGroupVO ogVO = getSelectedOfferGroup();
		for (OfferVO ovo : offers.getTarget()) {
			ovo.setParentOfferGroup(ogVO);
			try {
				offerService.createOffer(ovo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (OfferVO ovo : offers.getSource()) {
			ovo.setParentOfferGroup(null);
			try {
				offerService.createOffer(ovo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		selectedOfferGroup = null;
	}

	public void onRowSelect(SelectEvent event) {
		setName(getSelectedOfferGroup().getName());
		setDescription(getSelectedOfferGroup().getDescription());
	}

	public void onRowUnselect(UnselectEvent event) {
		selectedOfferGroup = null;
	}

	public void onTransfer(TransferEvent event) {

	}

	public void onSelect(SelectEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Item Selected", event.getObject().toString()));
	}

	public void onUnselect(UnselectEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Item Unselected", event.getObject().toString()));
	}

	public void onReorder() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"List Reordered", null));
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		OfferVO offerVO = new OfferVO();
		try {
			offerVO = offerService.getOfferById(Long.valueOf(arg2));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return offerVO;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		return ((OfferVO) arg2).getId().toString();
	}

}
