package hu.neuron.ier.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "Address")
public class Address extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private Long postcode;
	private String city;
	private String street;
	private String house;


	public Long getPostcode() {
		return postcode;
	}

	public void setPostcode(Long postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	@Override
	public String toString() {

		return postcode + " " + city + ", " + street + " " + house;
	}
}
