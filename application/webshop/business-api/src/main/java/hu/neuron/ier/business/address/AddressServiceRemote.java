package hu.neuron.ier.business.address;

import hu.neuron.ier.business.vo.AddressVO;

import java.util.List;

public interface AddressServiceRemote {

	AddressVO createAddress(AddressVO addressVO) throws Exception;

	void deleteAddress(Long id) throws Exception;

	List<AddressVO> getAddressByCity(String city) throws Exception;

	AddressVO updateAddress(AddressVO addressVO) throws Exception;
}
