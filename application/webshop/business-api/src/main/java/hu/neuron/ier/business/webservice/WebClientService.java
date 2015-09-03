package hu.neuron.ier.business.webservice;

import hu.neuron.ier.business.vo.ClientVO;

import java.util.List;

public interface WebClientService {

	public List<ClientVO> getClientsFromSales() throws Exception;
}
