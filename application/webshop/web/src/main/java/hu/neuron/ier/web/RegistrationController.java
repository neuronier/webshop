package hu.neuron.ier.web;

import hu.neuron.ier.business.address.AddressServiceRemote;
import hu.neuron.ier.business.client.ClientServiceRemote;
import hu.neuron.ier.business.vo.AddressVO;
import hu.neuron.ier.business.vo.ClientVO;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Controller for the login site.
 */
@ViewScoped
@ManagedBean(name = "registrationController")
public class RegistrationController implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userName = "";

	private String password = "";

	private String password2 = "";

	private String fullName = "";

	private String email = "";

	private String phone = "";
	
	private long postcode;
	
	private String city = "";
	
private String street = "";
	
	private String house = "";
	
	private long billingPostcode;
	
	private String billingCity = "";
	
	private String billingStreet = "";
	
	private String billingHouse = "";
	
	public long getPostcode() {
		return postcode;
	}



	

	@EJB(name = "ClientService", mappedName = "ClientService")
	private ClientServiceRemote clientService;
	
	@EJB(name = "AddressService", mappedName = "AddressService")
	private AddressServiceRemote addressService;

	public String addUser() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (!password.equals(getPassword2())) {
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Error!",
						"Password not match"));
				return null;
			} else if (clientService.findClientByName(userName) != null) {
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Error!",
						"Sorry we already have a user with this name"));
				return null;
			}

			ClientVO clientVO = new ClientVO();

			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			String encPassword = bCryptPasswordEncoder.encode(password);

			clientVO.setPassword(encPassword);
			clientVO.setUserName(userName);
			clientVO.setPhone(phone);
			clientVO.setEmail(email);
			clientVO.setFullName(fullName);
			
			
			
			AddressVO deliveryAddress = new AddressVO();
			AddressVO billAddress = new AddressVO();
			AddressVO deliveryAddress2 = new AddressVO();
			AddressVO billAddress2 = new AddressVO();

//			billAddress.setPostcode(billingPostcode);
//			billAddress.setHouse(billingHouse);
//			billAddress.setCity(billingCity);
//			billAddress.setStreet(billingStreet);
//
//			deliveryAddress.setPostcode(postcode);
//			deliveryAddress.setHouse(house);
//			deliveryAddress.setCity(city);
//			deliveryAddress.setStreet(street);
//			
			deliveryAddress2=addressService.createAddress(billAddress);
			billAddress2=addressService.createAddress(deliveryAddress);

			clientVO.setBillingAddress(billAddress2);

			clientVO.setDeliveryAddress(deliveryAddress2);
			
			

			clientService.registrationClient(clientVO);
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Info",
					"Registration successful you can log in now"));
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		}

		return "/pages/public/login.xhtml?faces-redirect=true";
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

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
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

	public long getBillingPostcode() {
		return billingPostcode;
	}

	public void setBillingPostcode(long billingPostcode) {
		this.billingPostcode = billingPostcode;
	}

	public String getBillingCity() {
		return billingCity;
	}

	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	public String getBillingStreet() {
		return billingStreet;
	}

	public void setBillingStreet(String billingStreet) {
		this.billingStreet = billingStreet;
	}

	public String getBillingHouse() {
		return billingHouse;
	}

	public void setBillingHouse(String billingHouse) {
		this.billingHouse = billingHouse;
	}

	public ClientServiceRemote getClientService() {
		return clientService;
	}

	public void setClientService(ClientServiceRemote clientService) {
		this.clientService = clientService;
	}
}
