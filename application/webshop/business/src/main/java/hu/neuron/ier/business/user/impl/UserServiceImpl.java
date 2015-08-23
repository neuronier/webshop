package hu.neuron.ier.business.user.impl;

import hu.neuron.ier.business.converter.RoleConverter;
import hu.neuron.ier.business.converter.UserConverter;
import hu.neuron.ier.business.user.UserServiceRemote;
import hu.neuron.ier.business.vo.RoleVO;
import hu.neuron.ier.business.vo.UserVO;
import hu.neuron.ier.core.dao.RoleDao;
import hu.neuron.ier.core.dao.UserDao;
import hu.neuron.ier.core.entity.Role;
import hu.neuron.ier.core.entity.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "UserService", name = "UserService")
@Remote(UserServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class UserServiceImpl implements UserServiceRemote, Serializable {

	private static final long serialVersionUID = -5212467642141604727L;

	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	@EJB
	UserConverter userConverter;

	@EJB
	RoleConverter roleConverter;

	@Override
	public UserVO findUserByName(String name) throws Exception {
		UserVO userVO = userConverter.toVO(userDao.findUserByName(name));

		return userVO;
	}

	@Override
	public UserVO registrationUser(UserVO userVO) throws Exception {
		User user = userDao.save(userConverter.toEntity(userVO));
		Role role = roleDao.findRoleByName("ROLE_USER");
		
		roleDao.addRoleToUser(user.getId(), role.getId());
		return userConverter.toVO(user);
	}

	@Override
	public List<UserVO> getUserList() throws Exception {
		List<UserVO> vos = userConverter.toVo(userDao.findAll());

		return vos;
	}

	@Override
	public List<UserVO> getUserList(int page, int pageSize, String sortField, int dir,
			String filter, String filterColumnName) throws Exception {

		Direction direction = dir == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, pageSize, new Sort(new Order(direction,
				sortField)));
		List<UserVO> vos = new ArrayList<UserVO>(pageSize);
		Page<User> entities;

		if (filter.length() != 0 && filterColumnName.equals("userName")) {
			entities = userDao.findByUserNameStartsWith(filter, pageRequest);
		} else {
			entities = userDao.findAll(pageRequest);
		}

		if (entities != null && entities.getSize() > 0) {
			List<User> contents = entities.getContent();
			for (User user : contents) {
				vos.add(userConverter.toVO(user));
			}
		}
		return vos;
	}

	@Override
	public RoleVO getRoleByName(String role) throws Exception {
		RoleVO roleVO = null;
		try {
			roleVO = roleConverter.toVo(roleDao.findRoleByName(role));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return roleVO;
	}

	@Override
	public UserVO saveUser(UserVO selectedUser) throws Exception {
		UserVO vo = userConverter.toVO(userDao.save(userConverter.toEntity(selectedUser)));
		return vo;
	}

}
