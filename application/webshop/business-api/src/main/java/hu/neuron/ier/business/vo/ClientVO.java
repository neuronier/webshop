package hu.neuron.ier.business.vo;

import java.io.Serializable;
import java.util.List;

public class ClientVO implements Serializable {

	private static final long serialVersionUID = 14889140889828633L;

	private Long id;
	private Long clientId;
	private String userName;
	private String password;
	private String fullName;
	private String email;
	private String phone;
	private AddressVO billingAddress;
	private AddressVO deliveryAddress;
	private List<RoleVO> roles;
//	private ShoppingCartVO shoppingCart;

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

	public AddressVO getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(AddressVO billAddress) {
		this.billingAddress = billAddress;
	}

	public AddressVO getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(AddressVO deliveryAddress) {
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

//	public ShoppingCartVO getShoppingCart() {
//		return shoppingCart;
//	}
//
//	public void setShoppingCart(ShoppingCartVO shoppingCart) {
//		this.shoppingCart = shoppingCart;
//	}
}
