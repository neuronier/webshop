package hu.neuron.ier.web;

import hu.neuron.ier.business.offer.OfferServiceRemote;
import hu.neuron.ier.business.offergroup.OfferGroupServiceRemote;
import hu.neuron.ier.business.vo.OfferGroupVO;
import hu.neuron.ier.business.vo.OfferVO;

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

/**
 * Vezérlő osztály az ajánlatcsoporttal kapcsolatos funkciókhoz.
 * 
 * @author Norbert
 *
 */
@ViewScoped
@ManagedBean(name = "offerGroupController")
public class OfferGroupController implements Serializable {

	private static final long serialVersionUID = 7624392370791189825L;

	private String name = "";
	private String description = "";
	private List<OfferGroupVO> allOfferGroup = null;
	private OfferGroupVO selectedOfferGroup = null;
	private Boolean selection = false;

	public Boolean getSelection() {
		return selection;
	}

	public void setSelection(Boolean selection) {
		this.selection = selection;
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
			selection = false;
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
			offerGroupVO.setParentOfferGroup(null);
			List<OfferVO> childOffers = offerService.getOffersByParentOfferGroup(offerGroupVO.getId());
			for(OfferVO offer : childOffers){
				offer.setParentOfferGroup(null);
				offerService.createOffer(offer);
			}
			offerGroupService.createOfferGroup(offerGroupVO);
			offerGroupService.deleteOfferGroup(offerGroupVO.getId());
			selection = false;
			getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onRowSelect(SelectEvent event) {
		selection = true;
		setName(getSelectedOfferGroup().getName());
		setDescription(getSelectedOfferGroup().getDescription());
	}

	public void onRowUnselect(UnselectEvent event) {
		selection = false;
		setName("");
		setDescription("");
	}

}
