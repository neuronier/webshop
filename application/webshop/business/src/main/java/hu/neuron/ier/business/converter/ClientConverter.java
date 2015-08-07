package hu.neuron.ier.business.converter;

import hu.neuron.ier.business.vo.ClientVO;
import hu.neuron.ier.core.entity.Client;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.interceptor.Interceptors;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Singleton
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class ClientConverter {

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	public ClientVO toVo(Client client) {
		if (client == null) {
			return null;
		}
		return mapper.map(client, ClientVO.class);
	}

	public List<ClientVO> toVo(List<Client> clients) {
		if (clients == null) {
			return null;
		}
		List<ClientVO> vos = new ArrayList<ClientVO>();
		for (Client client : clients) {
			vos.add(toVo(client));
		}

		return vos;
	}

	public Client toEntity(ClientVO vo) {
		if (vo == null) {
			return null;
		}

		return mapper.map(vo, Client.class);
	}

	public List<Client> toEntity(List<ClientVO> vos) {
		if (vos == null) {
			return null;
		}
		List<Client> clients = new ArrayList<Client>();
		for (ClientVO clientVO : vos) {
			clients.add(toEntity(clientVO));
		}

		return clients;
	}

}
