
package hu.neuron.ier.web;

import hu.neuron.ier.business.client.ClientServiceRemote;
import hu.neuron.ier.business.vo.ClientVO;
import hu.neuron.ier.business.vo.RoleVO;
import hu.neuron.ier.business.vo.UserVO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customClientDetailsService")
public class CustomClientDetailsService implements UserDetailsService {

	@EJB(name = "ClientService", mappedName = "ClientService")
	private ClientServiceRemote clientService;

	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {

		ClientVO user;
		try {
			user = clientService.findClientByName(username);

			if (user == null) {
				throw new UsernameNotFoundException(username);
			}
			List<GrantedAuthority> authorities = buildUserAuthority(user
					.getRoles());

			return buildUserForAuthentication(user, authorities);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(e.getMessage());
		}

	}

	private User buildUserForAuthentication(ClientVO client,
			List<GrantedAuthority> authorities) {
		return new User(client.getUserName(), client.getPassword(), true, true,
				true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(List<RoleVO> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		for (RoleVO userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getName()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(
				setAuths);

		return Result;
	}

}