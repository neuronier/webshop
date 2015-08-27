package hu.neuron.ier.core.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The Class User.
 */

@Entity
@Table(name = "User")
@NamedQuery(name = "User.findUserByName", query = "SELECT u FROM User u  WHERE u.userName = :userName")
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private String fullName;
	private String email;
	private String phone;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role_sw")
	private Collection<Role> roles;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", getId()=" + getId()
				+ "]";
	}

}
