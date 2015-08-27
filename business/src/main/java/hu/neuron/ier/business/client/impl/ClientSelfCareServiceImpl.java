package hu.neuron.ier.business.client.impl;

import hu.neuron.ier.business.client.ClientSelfCareServiceRemote;
import hu.neuron.ier.business.converter.ClientConverter;
import hu.neuron.ier.business.converter.RoleConverter;
import hu.neuron.ier.business.vo.ClientVO;
import hu.neuron.ier.core.dao.ClientDao;
import hu.neuron.ier.core.dao.RoleDao;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(name = "ClientSelfCareService", mappedName = "ClientSelfCareService")
@Remote(ClientSelfCareServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class ClientSelfCareServiceImpl implements ClientSelfCareServiceRemote, Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	ClientDao clientDao;

	@Autowired
	RoleDao roleDao;

	@EJB
	RoleConverter roleConverter;

	@EJB
	ClientConverter clientConverter;

	@Override
	public boolean updateClient(ClientVO clientVO) throws Exception {
		clientDao.save(clientConverter.toEntity(clientVO));
		return true;
	}

	@Override
	public ClientVO getClientByName(String client) {
		ClientVO clientVO = null;
		try {
			clientVO = clientConverter.toVo(clientDao.findByUserName(client));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientVO;
	}

	@Override
	public ClientVO getClientByEmail(String email) {
		ClientVO clientVO = null;
		try {
			clientVO = clientConverter.toVo(clientDao.findUserByEmail(email));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientVO;
	}

}