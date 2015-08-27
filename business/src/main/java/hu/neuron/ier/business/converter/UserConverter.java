package hu.neuron.ier.business.converter;

import hu.neuron.ier.business.vo.UserVO;
import hu.neuron.ier.core.entity.User;

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
public class UserConverter {

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	public UserVO toVO(User user) {
		if (user == null) {
			return null;
		}

		return mapper.map(user, UserVO.class);
	}

	public User toEntity(UserVO userVO) {
		if (userVO == null) {
			return null;
		}

		return mapper.map(userVO, User.class);
	}

	public List<UserVO> toVo(List<User> users) {
		if (users == null) {
			return null;
		}
		List<UserVO> vos = new ArrayList<UserVO>();
		for (User user : users) {
			vos.add(toVO(user));
		}

		return vos;
	}

	public List<User> toEntity(List<UserVO> vos) {
		if (vos == null) {
			return null;
		}
		List<User> users = new ArrayList<User>();
		for (UserVO vo : vos) {
			users.add(toEntity(vo));
		}

		return users;

	}
}
