package hu.neuron.ier.business.converter;

import hu.neuron.ier.business.vo.AddressVO;
import hu.neuron.ier.core.entity.Address;

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
public class AddressConverter {

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	public AddressVO toVO(Address address) {
		if (address == null) {
			return null;
		}
		return mapper.map(address, AddressVO.class);
	}

	public Address toEntity(AddressVO addressVO) {
		if (addressVO == null) {
			return null;
		}
		return mapper.map(addressVO, Address.class);
	}

	public List<AddressVO> toVO(List<Address> addresses) {
		if (addresses == null) {
			return null;
		}
		List<AddressVO> vos = new ArrayList<AddressVO>();
		for (Address address : addresses) {
			vos.add(toVO(address));
		}

		return vos;
	}

	public List<Address> toEntity(List<AddressVO> vos) {
		if (vos == null) {
			return null;
		}
		List<Address> addresses = new ArrayList<Address>();
		for (AddressVO vo : vos) {
			addresses.add(toEntity(vo));
		}

		return addresses;
	}
}
