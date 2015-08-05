package hu.neuron.ier.core.entity;

import hu.neuron.ier.core.Address;
import hu.neuron.ier.core.RoleVO;

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

	private Long id;
	private Long clientId;
	private String userName;
	private String password;
	private String name;
	private String email;
	private String phone;
	private Address billingAddress;
	private Address deliveryAddress;
	private List<RoleVO> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<RoleVO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleVO> roles) {
		this.roles = roles;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

}
