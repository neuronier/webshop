package hu.neuron.ier.business.client;

import hu.neuron.ier.business.vo.ClientVO;

public interface ClientSelfCareServiceRemote {

	public boolean updateClient(ClientVO clientVO) throws Exception;

	public ClientVO getClientByName(String client);

	public ClientVO getClientByEmail(String email);

}