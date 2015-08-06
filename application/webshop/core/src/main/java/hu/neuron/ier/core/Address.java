package hu.neuron.ier.core;

public class Address {

	private Long id;
	private long postcode;
	private String city;
	private String street;
	private String house;

	public Address(Long id, long postcode, String city, String street, String house) {
		super();
		this.id = id;
		this.postcode = postcode;
		this.city = city;
		this.street = street;
		this.house = house;
	}

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
