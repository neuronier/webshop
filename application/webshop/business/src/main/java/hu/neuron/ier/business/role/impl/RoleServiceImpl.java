package hu.neuron.ier.business.role.impl;

import hu.neuron.ier.business.converter.RoleConverter;
import hu.neuron.ier.business.converter.UserConverter;
import hu.neuron.ier.business.role.RoleServiceRemote;
import hu.neuron.ier.business.vo.RoleVO;
import hu.neuron.ier.business.vo.UserVO;
import hu.neuron.ier.core.dao.RoleDao;
import hu.neuron.ier.core.dao.UserDao;
import hu.neuron.ier.core.entity.Role;

import java.io.Serializable;
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

@Stateless(mappedName = "RoleService", name = "RoleService")
@Remote(RoleServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class RoleServiceImpl implements RoleServiceRemote, Serializable {

	private static final long serialVersionUID = 4262658338551481527L;

	@Autowired
	UserDao userDao;
	@Autowired
	RoleDao roleDao;

	@EJB
	RoleConverter roleConverter;

	@EJB
	UserConverter userConverter;

	@Override
	public UserVO setUpRoles(UserVO userVO) throws Exception {
		List<Role> roles;
		try {
			roles = roleDao.findRolesByUserId(userVO.getId());
			userVO.setRoles(roleConverter.toVo(roles));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userVO;
	}

	@Override
	public int getRowNumber() throws Exception {

		return (int) userDao.count();
	}

	@Override
	public List<RoleVO> getRoles() throws Exception {

		return roleConverter.toVo(roleDao.findAll());
	}

	@Override
	public RoleVO getRoleByName(String name) throws Exception {
		RoleVO roleVO = null;
		try {
			roleVO = roleConverter.toVo(roleDao.findRoleByName(name));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roleVO;
	}

	@Override
	public void saveRole(RoleVO roleVO) throws Exception {
		roleDao.save(roleConverter.toEntity(roleVO));

	}

	@Override
	public void updateRole(RoleVO roleVO) throws Exception {
		saveRole(roleVO);

	}

	@Override
	public void removeRole(RoleVO roleVO) throws Exception {
		roleDao.delete(roleVO.getId());

	}

	@Override
	public int getRoleCount() {

		return (int) roleDao.count();
	}

	@Override
	public List<RoleVO> getRoles(int page, int pageSize, String sortField, int dir, String filter,
			String filterColumnName) throws Exception {
		Direction direction = dir == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, pageSize, new Sort(new Order(direction,
				sortField)));
		Page<Role> entities;

		if (filter.length() != 0 && filterColumnName.equals("name")) {
			entities = roleDao.findByNameStartsWith(filter, pageRequest);
		} else {
			entities = roleDao.findAll(pageRequest);
		}

		List<RoleVO> vos = roleConverter.toVo(entities.getContent());

		return vos;
	}

}
