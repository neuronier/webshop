package hu.neuron.ier.core.entity;

import hu.neuron.ier.core.entity.Address;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The Class of Client.
 */
@Entity
@Table(name = "Client")
public class Client extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Long clientId;
	private String userName;
	private String password;
	private String fullName;
	private String email;
	private String phone;
	private Address billingAddress;
	private Address deliveryAddress;
	private List<Role> roles;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billAddress) {
		this.billingAddress = billAddress;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

}
