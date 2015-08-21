package hu.neuron.ier.web;

import hu.neuron.ier.business.offer.OfferServiceRemote;
import hu.neuron.ier.business.vo.OfferVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Controller for the admin site.
 */

@ManagedBean(name = "offerController")
@ApplicationScoped
public class OfferController implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The manage user facade service. */

	@EJB(name = "OfferService", mappedName = "OfferService")
	private OfferServiceRemote offerService;

	List<OfferVO> list;
	private Long id;
	private Long newCost;
	private Long originalCost;
	private String name;
	private String description;

	public List<OfferVO> createOffers() {
		try {

			list = new ArrayList<OfferVO>();

			list = offerService.getFeaturedOffers(true);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;

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

}
