package hu.neuron.ier.web;
 
import hu.neuron.ier.business.vo.PurchaseVO;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
 
@ManagedBean(name = "tabbedView")
public class TabbedView {
     
    private List<PurchaseVO> purchases;
    
    @ManagedProperty("#{clientSelfCareController}")
	private ClientSelfCareController service;
 
    @PostConstruct
    public void init() {
        purchases = service.createPurchases();
    }
     
   public List<PurchaseVO> getPurchases() {
		return purchases;
	}



	public ClientSelfCareController getService() {
	return service;
}

public void setService(ClientSelfCareController service) {
	this.service = service;
}

	public void setPurchases(List<PurchaseVO> purchases) {
		this.purchases = purchases;
	}



	public void onTabChange(TabChangeEvent event) {
        FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
         
    public void onTabClose(TabCloseEvent event) {
        FacesMessage msg = new FacesMessage("Tab Closed", "Closed tab: " + event.getTab().getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}