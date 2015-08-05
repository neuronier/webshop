package hu.neuron.ier.business.user;

import hu.neuron.ier.business.vo.RoleVO;
import hu.neuron.ier.business.vo.UserVO;

import java.util.List;

public interface UserServiceRemote {

	UserVO findUserByName(String name) throws Exception;

	void registrationUser(UserVO userVO) throws Exception;

	List<UserVO> getUserList() throws Exception;

	List<UserVO> getUserList(int i, int pageSize, String sortField, int dir, String filter,
			String filterColumnName) throws Exception;

	RoleVO getRoleByName(String role) throws Exception;

	void saveUser(UserVO selectedUser) throws Exception;

}
