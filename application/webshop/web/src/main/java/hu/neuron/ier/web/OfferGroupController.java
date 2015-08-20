package hu.neuron.ier.web;

import hu.neuron.ier.business.offergroup.OfferGroupServiceRemote;
import hu.neuron.ier.business.vo.OfferGroupVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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

	@EJB(name = "OfferGroupService", mappedName = "OfferGroupService")
	OfferGroupServiceRemote offerGroupService;

	public void createOfferGroup() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			OfferGroupVO offerGroupVO = new OfferGroupVO();
			offerGroupVO.setName(getName());
			offerGroupVO.setDescription(getDescription());
			offerGroupService.createOfferGroup(offerGroupVO);
			this.name = "";
			this.description = "";
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,null,"Sikertelen mentés!"));
			
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

}
