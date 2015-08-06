package hu.neuron.ier.business.converter;

import hu.neuron.ier.business.vo.RoleVO;
import hu.neuron.ier.core.entity.Role;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.interceptor.Interceptors;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Singleton
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class RoleConverter {

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	public RoleVO toVo(Role role) {
		if (role == null) {
			return null;
		}

		return mapper.map(role, RoleVO.class);
	}

	public Role toEntity(RoleVO roleVO) {
		if (roleVO == null) {
			return null;
		}

		return mapper.map(roleVO, Role.class);
	}

	public List<RoleVO> toVo(List<Role> roles) {
		if (roles == null) {
			return null;
		}
		List<RoleVO> vos = new ArrayList<RoleVO>();
		for (Role role : roles) {
			vos.add(toVo(role));
		}

		return vos;
	}

	public List<Role> toEntity(List<RoleVO> vos) {
		if (vos == null) {
			return null;
		}
		List<Role> roles = new ArrayList<Role>();
		for (RoleVO vo : vos) {
			roles.add(toEntity(vo));
		}

		return roles;
	}

}
