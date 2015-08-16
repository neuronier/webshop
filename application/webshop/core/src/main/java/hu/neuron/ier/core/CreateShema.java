package hu.neuron.ier.core;

import hu.neuron.ier.core.dao.RoleDao;
import hu.neuron.ier.core.dao.UserDao;
import hu.neuron.ier.core.entity.Role;
import hu.neuron.ier.core.entity.User;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CreateShema {
	private static final Logger logger = Logger.getLogger(CreateShema.class);

	@Autowired
	public RoleDao roleDAO;
	@Autowired
	public UserDao userDAO;
	public void insertRoles() {
		Role dto = null;
		try {
			if (roleDAO.findRoleByName("ROLE_USER") == null) {
				dto = new Role();
				dto.setName("ROLE_USER");
				roleDAO.save(dto);
			}
			if (roleDAO.findRoleByName("ROLE_ADMIN") == null) {
				dto = new Role();
				dto.setName("ROLE_ADMIN");
				roleDAO.save(dto);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public void insertUsersAndAddRole() {
		try {
			if (userDAO.findUserByName("admin") == null) {
				List<Role> roles = new ArrayList<Role>();
				Role role;
				User dto = new User();
				dto.setUserName("manager");
				dto.setPassword("pass");
				dto.setEmail("manager@email.hu");
				dto.setPhone("4421123");
				dto.setFullName("Full Name");
				

				role = roleDAO.findRoleByName("ROLE_ADMIN");
				roles.add(role);
				role = roleDAO.findRoleByName("ROLE_USER");
				roles.add(role);

				dto.setRoles(roles);
				userDAO.save(dto);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
