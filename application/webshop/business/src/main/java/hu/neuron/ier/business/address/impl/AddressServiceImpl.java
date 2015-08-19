package hu.neuron.ier.business.address.impl;

import hu.neuron.ier.business.address.AddressServiceRemote;
import hu.neuron.ier.business.converter.AddressConverter;
import hu.neuron.ier.business.vo.AddressVO;
import hu.neuron.ier.core.dao.AddressDao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "AddressService", name = "AddressService")
@Remote(AddressServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class AddressServiceImpl implements AddressServiceRemote, Serializable {

	private static final long serialVersionUID = 2601868127317540891L;

	@Autowired
	AddressDao addressDao;

	@EJB
	AddressConverter addressConverter;

	@Override
	public AddressVO createAddress(AddressVO addressVO) throws Exception {
		AddressVO vo = addressConverter.toVO(addressDao.save(addressConverter.toEntity(addressVO)));
		return vo;

	}

	@Override
	public void deleteAddress(Long id) throws Exception {
		addressDao.delete(id);

	}

	@Override
	public List<AddressVO> getAddressByCity(String city) throws Exception {

		return addressConverter.toVO(addressDao.findAddressByCity(city));
	}

	@Override
	public void updateAddress(AddressVO addressVO) throws Exception {
		createAddress(addressVO);

	}

}
