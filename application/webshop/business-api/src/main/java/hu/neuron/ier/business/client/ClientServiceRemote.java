package hu.neuron.ier.business.client;

import hu.neuron.ier.business.vo.ClientVO;
import hu.neuron.ier.business.vo.RoleVO;

import java.util.List;

public interface ClientServiceRemote {

	ClientVO findClientByName(String name) throws Exception;

	void registrationClient(ClientVO clientVO) throws Exception;

	List<ClientVO> getClientList() throws Exception;

	List<ClientVO> getClientList(int i, int pageSize, String sortField, int dir, String filter,
			String filterColumnName) throws Exception;

	RoleVO getRoleByName(String role) throws Exception;

	void saveClient(ClientVO clientVO) throws Exception;

}
