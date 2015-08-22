package hu.neuron.ier.business.admin.impl;

import hu.neuron.ier.business.admin.AdminServiceRemote;
import hu.neuron.ier.business.role.RoleServiceRemote;
import hu.neuron.ier.business.user.UserServiceRemote;
import hu.neuron.ier.business.vo.RoleVO;
import hu.neuron.ier.business.vo.UserVO;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "AdminService", name = "AdminService")
@Remote(AdminServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class AdminServiceImpl implements AdminServiceRemote, Serializable {
	private static final long serialVersionUID = -1296528259371447796L;

	

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	UserServiceRemote userService;

	@EJB(mappedName = "RoleService", name = "RoleService")
	RoleServiceRemote roleServiceRemote;

	public AdminServiceImpl() {
	}

	@Override
	public UserVO findUserAndRolesByName(String name) throws Exception {
		
		UserVO userVO = userService.findUserByName(name);
		if (userVO != null) {
			userVO = roleServiceRemote.setUpRoles(userVO);
		}
		return userVO;
	}

	@Override
	public void registrationUser(UserVO userVO) throws Exception {
		userService.registrationUser(userVO);

	}

	@Override
	public UserVO findUserByName(String name) throws Exception {

		return userService.findUserByName(name);
	}

	@Override
	public List<UserVO> getUserList(int i, int pageSize, String sortField,
			int dir, String filter, String filterColumnName) throws Exception {

		return userService.getUserList(i, pageSize, sortField, dir, filter,
				filterColumnName);
	}

	@Override
	public RoleVO getRoleByName(String role) throws Exception {

		return userService.getRoleByName(role);
	}

	@Override
	public void saveUser(UserVO selectedUser) throws Exception {
		userService.saveUser(selectedUser);
	}

	@Override
	public UserVO setUpRoles(UserVO vo) throws Exception {

		return roleServiceRemote.setUpRoles(vo);
	}

	@Override
	public int getRowNumber() throws Exception {

		return roleServiceRemote.getRowNumber();
	}

	@Override
	public List<RoleVO> getRoles() throws Exception {

		return roleServiceRemote.getRoles();
	}

	@Override
	public void saveRole(RoleVO roleVO) throws Exception {
		roleServiceRemote.saveRole(roleVO);

	}

	@Override
	public void updateRole(RoleVO roleVO) throws Exception {
		roleServiceRemote.updateRole(roleVO);
	}

	@Override
	public void removeRole(RoleVO roleVO) throws Exception {
		roleServiceRemote.removeRole(roleVO);

	}

	@Override
	public List<RoleVO> getRoles(int i, int pageSize, String sortField,
			int dir, String filter, String filterColumnName) throws Exception {
		return roleServiceRemote.getRoles(i, pageSize, sortField, dir, filter,
				filterColumnName);
	}

	@Override
	public int getRoleCount() {
		return roleServiceRemote.getRoleCount();
	}

	@Override
	public List<UserVO> getUserList() throws Exception {
		return userService.getUserList();

	}
	
	
}