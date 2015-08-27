package hu.neuron.ier.business.role;

import hu.neuron.ier.business.vo.RoleVO;
import hu.neuron.ier.business.vo.UserVO;

import java.util.List;

public interface RoleServiceRemote {

	UserVO setUpRoles(UserVO userVO) throws Exception;

	int getRowNumber() throws Exception;

	List<RoleVO> getRoles() throws Exception;

	List<RoleVO> getRoles(int page, int pageSize, String sortField, int dir, String filter,
			String filterColumnName) throws Exception;

	RoleVO getRoleByName(String name) throws Exception;

	void saveRole(RoleVO roleVO) throws Exception;

	void updateRole(RoleVO roleVO) throws Exception;

	void removeRole(RoleVO roleVO) throws Exception;

	int getRoleCount();

}
