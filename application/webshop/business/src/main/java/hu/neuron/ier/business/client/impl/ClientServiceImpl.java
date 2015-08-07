package hu.neuron.ier.business.client.impl;

import hu.neuron.ier.business.client.ClientServiceRemote;
import hu.neuron.ier.business.converter.ClientConverter;
import hu.neuron.ier.business.converter.RoleConverter;
import hu.neuron.ier.business.vo.ClientVO;
import hu.neuron.ier.business.vo.RoleVO;
import hu.neuron.ier.core.dao.ClientDao;
import hu.neuron.ier.core.dao.RoleDao;
import hu.neuron.ier.core.entity.Client;
import hu.neuron.ier.core.entity.Role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public class ClientServiceImpl implements ClientServiceRemote, Serializable {

	private static final long serialVersionUID = -5684405871775843513L;

	@Autowired
	ClientDao clientDao;
	@Autowired
	RoleDao roleDao;

	@EJB
	ClientConverter clientConverter;
	@EJB
	RoleConverter roleConverter;

	@Override
	public ClientVO findClientByName(String name) throws Exception {

		return clientConverter.toVo(clientDao.findbyClientByClientName(name));
	}

	@Override
	public void registrationClient(ClientVO clientVO) throws Exception {
		Client client = clientDao.save(clientConverter.toEntity(clientVO));
		Role role = roleDao.findRoleByName("ROLE_USER");
		// itt két lehetőség is van, a client id-ja vagy a clientId-ja
		roleDao.addRoleToUser(role.getId(), client.getId());
	}

	@Override
	public List<ClientVO> getClientList() throws Exception {

		return clientConverter.toVo(clientDao.findAll());
	}

	@Override
	public List<ClientVO> getClientList(int i, int pageSize, String sortField, int dir,
			String filter, String filterColumnName) throws Exception {

		Direction direction = dir == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(i, pageSize, new Sort(new Order(direction,
				sortField)));
		List<ClientVO> vos = new ArrayList<ClientVO>(pageSize);
		Page<Client> entities = null;

		if (filter.length() != 0 && filterColumnName.equals("userName")) {
			// kellene szerintem a clientDAO-ba egy findByUserNameStartsWith()
			// metódus
		} else {
			entities = clientDao.findAll(pageRequest);
		}

		if (entities != null && entities.getSize() > 0) {
			List<Client> contents = entities.getContent();
			for (Client client : contents) {
				vos.add(clientConverter.toVo(client));
			}
		}

		return vos;
	}

	@Override
	public RoleVO getRoleByName(String role) throws Exception {

		return roleConverter.toVo(roleDao.findRoleByName(role));
	}

	@Override
	public void saveClient(ClientVO clientVO) throws Exception {
		clientDao.save(clientConverter.toEntity(clientVO));

	}

}