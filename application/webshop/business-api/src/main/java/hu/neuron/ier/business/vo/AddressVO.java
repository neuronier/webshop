package hu.neuron.ier.business.vo;

import java.io.Serializable;

public class AddressVO implements Serializable {

	private static final long serialVersionUID = 3480909071433780347L;
	private Long id;
	private long postcode;
	private String city;
	private String street;
	private String house;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getPostcode() {
		return postcode;
	}

	public void setPostcode(long postcode) {
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
