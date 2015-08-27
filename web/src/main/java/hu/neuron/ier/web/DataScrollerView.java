package hu.neuron.ier.web;
import hu.neuron.ier.business.vo.OfferVO;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
 
@ManagedBean(name="dataScrollerView")
@ViewScoped
public class DataScrollerView implements Serializable {
     
    private List<OfferVO> offers;
    
   @ManagedProperty("#{searchController}")
    private SearchController service;
    
    @PostConstruct
    public void init() {
        offers = service.doSearch();
    }
	public List<OfferVO> getOffers() {
		return offers;
	}

	public void setOffers(List<OfferVO> offers) {
		this.offers = offers;
	}
	
	public SearchController getService() {
		return service;
	}
	public void setService(SearchController service) {
		this.service = service;
	}


	
     
}