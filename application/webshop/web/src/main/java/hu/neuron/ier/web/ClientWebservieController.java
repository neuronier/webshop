package hu.neuron.ier.web;

import hu.neuron.ier.business.vo.ClientVO;
import hu.neuron.ier.business.webservice.WebClientService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "clientWebservieController")
public class ClientWebservieController implements Serializable {

	private static final long serialVersionUID = -8824002143072786538L;

	@EJB(name = "WebClientService", mappedName = "WebClientService")
	WebClientService webClientService;

	private List<ClientVO> integratedClients = new ArrayList<ClientVO>();

	public void integrateClients() {
		try {
			integratedClients.addAll(webClientService.getClientsFromSales());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<ClientVO> getIntegratedClients() {
		return integratedClients;
	}

	public void setIntegratedClients(List<ClientVO> integratedClients) {
		this.integratedClients = integratedClients;
	}

}
