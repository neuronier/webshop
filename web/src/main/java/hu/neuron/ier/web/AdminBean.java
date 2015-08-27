package hu.neuron.ier.web;

import hu.neuron.ier.business.admin.AdminServiceRemote;
import hu.neuron.ier.business.user.UserServiceRemote;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "adminBean")
public class AdminBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private LazyUserModel userModel;

	@EJB(name = "AdminService", mappedName = "AdminService")
	private AdminServiceRemote adminService;

	@PostConstruct
	public void init() {
		userModel = new LazyUserModel(adminService);
	}

	public LazyUserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(LazyUserModel userModel) {
		this.userModel = userModel;
	}

	public AdminServiceRemote getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminServiceRemote adminService) {
		this.adminService = adminService;
	}

}