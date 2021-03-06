package hu.neuron.ier.core.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The Class of Client.
 */
@Entity
@Table(name = "Client")
// @NamedQuery(name = "Client.findByBClientByName", query =
// "SELECT c FROM Client c  WHERE c.userName = :userName")
public class Client extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String clientId;
	private String userName;
	private String password;
	private String fullName;
	private String email;
	private String phone;
	@ManyToOne(fetch = FetchType.LAZY)
	private Address billingAddress;
	@ManyToOne(fetch = FetchType.LAZY)
	private Address deliveryAddress;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "client_role_sw")
	private List<Role> roles;
	@OneToOne
	private ShoppingCart shoppingCart;

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

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

}
