package hu.neuron.ier.business.client.impl;

import hu.neuron.ier.business.client.ClientServiceRemote;
import hu.neuron.ier.business.converter.ClientConverter;
import hu.neuron.ier.business.converter.RoleConverter;
import hu.neuron.ier.business.vo.AddressVO;
import hu.neuron.ier.business.vo.ClientVO;
import hu.neuron.ier.business.vo.RoleVO;
import hu.neuron.ier.core.dao.ClientDao;
import hu.neuron.ier.core.dao.RoleDao;
import hu.neuron.ier.core.dao.ShoppingCartDao;
import hu.neuron.ier.core.entity.Client;
import hu.neuron.ier.core.entity.Role;
import hu.neuron.ier.core.entity.ShoppingCart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "ClientService", name = "ClientService")
@Remote(ClientServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class ClientServiceImpl implements ClientServiceRemote, Serializable {

	private static final long serialVersionUID = -5684405871775843513L;

	@Autowired
	ClientDao clientDao;
	@Autowired
	RoleDao roleDao;
	@Autowired
	ShoppingCartDao shoppingCartDao;

	@EJB
	ClientConverter clientConverter;
	@EJB
	RoleConverter roleConverter;

	@Override
	public ClientVO findClientByName(String name) throws Exception {

		return clientConverter.toVo(clientDao.findByUserName(name));
	}

	@Override
	public ClientVO registrationClient(ClientVO clientVO) throws Exception {
		Client client = clientConverter.toEntity(clientVO);
		Role role = roleDao.findRoleByName("ROLE_CLIENT");
		//jogok lekérdezése
		List<Role> roles = client.getRoles();
		if (roles == null) {
			//ha null, akkor létrehozzuk a listát
			roles = new ArrayList<Role>();
		}// jog hozzáadás, ha még nincs
		if (!roles.contains(role)) {
			roles.add(role);
		}
		//jogok beállítása
		client.setRoles(roles);

		// kosár létrehozása
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart = shoppingCartDao.save(shoppingCart);
		// ügyfélhez hozzáadás
		client.setShoppingCart(shoppingCart);
		client = clientDao.save(client);

		// itt két lehetőség is van, a client id-ja vagy a clientId-ja
		// roleDao.addRoleToClient(role.getId(), client.getId());

		return clientConverter.toVo(client);

	}

	@Override
	public List<ClientVO> getClientList() throws Exception {

		return clientConverter.toVo(clientDao.findAll());
	}

	@Override
	public List<ClientVO> getClientList(int i, int pageSize, String sortField,
			int dir, String filter, String filterColumnName) throws Exception {

		Direction direction = dir == 1 ? Sort.Direction.ASC
				: Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(i, pageSize, new Sort(
				new Order(direction, sortField)));
		List<ClientVO> vos = new ArrayList<ClientVO>(pageSize);
		Page<Client> entities = null;

		if (filter.length() != 0 && filterColumnName.equals("userName")) {
			entities = clientDao.findByUserNameStartsWith(filter, pageRequest);
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
	public ClientVO saveClient(ClientVO clientVO) throws Exception {
		ClientVO vo = clientConverter.toVo(clientDao.save(clientConverter
				.toEntity(clientVO)));
		return vo;
	}

	@Override
	public ClientVO addAddressToClient(Long clientId, boolean isAddressMatch,
			AddressVO billingAddress, AddressVO deliveryAddress)
			throws Exception {

		ClientVO clientVO = new ClientVO();
		clientVO = clientConverter.toVo(clientDao.findOne(clientId));
		clientVO.setBillingAddress(billingAddress);
		if (isAddressMatch) {
			clientVO.setDeliveryAddress(billingAddress);
		} else {
			clientVO.setDeliveryAddress(deliveryAddress);
		}
		saveClient(clientVO);

		return clientVO;
	}
}
