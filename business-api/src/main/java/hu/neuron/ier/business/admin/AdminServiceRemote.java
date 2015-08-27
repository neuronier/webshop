package hu.neuron.ier.business.admin;

import hu.neuron.ier.business.vo.RoleVO;
import hu.neuron.ier.business.vo.UserVO;

import java.util.List;

public interface AdminServiceRemote {

	public UserVO findUserAndRolesByName(String name) throws Exception;

	public void registrationUser(UserVO userVO) throws Exception;

	public UserVO findUserByName(String name) throws Exception;

	public List<UserVO> getUserList(int i, int pageSize, String sortField,
			int dir, String filter, String filterColumnName) throws Exception;


	public void saveUser(UserVO selectedUser) throws Exception;

	public UserVO setUpRoles(UserVO vo) throws Exception;

	public int getRowNumber() throws Exception;

	public List<RoleVO> getRoles() throws Exception ;

	public RoleVO getRoleByName(String role) throws Exception;

	public void saveRole(RoleVO roleVO) throws Exception;

	public void updateRole(RoleVO roleVO) throws Exception;

	public void removeRole(RoleVO roleVO) throws Exception;

	public List<RoleVO> getRoles(int i, int pageSize, String sortField,
			int dir, String filter, String filterColumnName) throws Exception;

	public int getRoleCount() throws Exception;

	public List<UserVO> getUserList() throws Exception;
	
}