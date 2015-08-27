package hu.neuron.ier.web;

import hu.neuron.ier.business.offer.OfferServiceRemote;
import hu.neuron.ier.business.offergroup.OfferGroupServiceRemote;
import hu.neuron.ier.business.vo.OfferGroupVO;
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
@ManagedBean(name = "searchController")
@ApplicationScoped
public class SearchController implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	List<OfferVO> offers;
	List<OfferGroupVO> offerGroups;
	
	private String key;
	private String name;
	private String newCost;
	private String description;
	OfferVO offer = new OfferVO();

	@EJB(name = "OfferGroupService", mappedName = "OfferGroupService")
	private OfferGroupServiceRemote offerGroupService;

	@EJB(name = "OfferService", mappedName = "OfferService")
	private OfferServiceRemote offerService;

	public List<OfferVO> doSearch() {

		try {
			// offerGroups = offerGroupService.searchOfferGroups(keyword);
			offers =  new ArrayList<OfferVO>();
			offerGroups =  new ArrayList<OfferGroupVO>();
			
			offerGroups = offerGroupService.searchOfferGroups(key);
            for (OfferGroupVO offerGroupVO : offerGroups) {
            	List<OfferVO> childOffers = new ArrayList<OfferVO>();
            	childOffers=offerService.getOffersByParentOfferGroup(offerGroupVO.getId());
            	for (OfferVO childOfferVO : childOffers) {
            		if (!(offers.contains(childOfferVO))) {
            			offers.add(childOfferVO);
            		}
            		
				}
            	
            	
			}
//           	List<OfferVO> myOffers = new ArrayList<OfferVO>();
           
			offerService.searchOffers(key) ;
			for (OfferVO myOfferVO : offerService.searchOffers(key)) {
        		if (!(offers.contains(myOfferVO))) {
        			offers.add(myOfferVO);
        		}
        		
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return offers;
	}

	public String getNewCost() {
		return newCost;
	}

	public void setNewCost(String newCost) {
		this.newCost = newCost;
	}

	public OfferVO getOffer() {
		return offer;
	}

	public void setOffer(OfferVO offer) {
		this.offer = offer;
	}

	public List<OfferVO> getOffers() {
		return offers;
	}

	public void setOffers(List<OfferVO> offers) {
		this.offers = offers;
	}

	public List<OfferGroupVO> getOfferGroups() {
		return offerGroups;
	}

	public void setOfferGroups(List<OfferGroupVO> offerGroups) {
		this.offerGroups = offerGroups;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public OfferGroupServiceRemote getOfferGroupService() {
		return offerGroupService;
	}

	public void setOfferGroupService(OfferGroupServiceRemote offerGroupService) {
		this.offerGroupService = offerGroupService;
	}

	public OfferServiceRemote getOfferService() {
		return offerService;
	}

	public void setOfferService(OfferServiceRemote offerService) {
		this.offerService = offerService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNewPrice() {
		return newCost;
	}

	public void setNewPrice(String newPrice) {
		this.newCost = newPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}