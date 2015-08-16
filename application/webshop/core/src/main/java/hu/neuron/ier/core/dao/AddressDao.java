package hu.neuron.ier.core.dao;

import hu.neuron.ier.core.entity.Address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface AddressDao extends JpaRepository<Address, Long> {

	List<Address> findAddressByCity(String city) throws Exception;

	Address findAddressByPostcodeAndCityAndStreetAndHouse(Long postcode,
			String city, String street, String house) throws Exception;

}
